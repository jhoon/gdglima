package pe.gdglima.devfestlima.engine.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import javax.inject.Named;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JDOCursorHelper;
import com.google.appengine.api.search.Consistency;
import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Field;
import com.google.appengine.api.search.Index;
import com.google.appengine.api.search.IndexSpec;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;
import com.google.appengine.api.search.SearchServiceFactory;
import com.google.api.server.spi.response.CollectionResponse;

@Api(name = "speakers", description = "Esta entidad representa a los expositores.", version = "v1")
public class SpeakersEndpoint {

	@ApiMethod(httpMethod = "POST", name = "speakers.insert", path = "speakers/insert")
	public Speakers insertSpeakers(Speakers speakers) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			mgr.makePersistent(speakers);
			addToSearchIndex(speakers);
			System.out.println(":::::::::::::::::::::::::ID: " + speakers.getId());
		} finally {
			mgr.close();
		}
		return speakers;
	}

	@SuppressWarnings("unchecked")
	@ApiMethod(httpMethod = "GET", name = "speakers.list", path = "speakers/search/{queryString}")
	public List<Speakers> search(@Named("queryString") String queryString) throws Exception {
		System.out.println("queryString:  " + queryString);

		PersistenceManager mgr = getPersistenceManager();
		List<Speakers> returnList = new ArrayList<Speakers>();

		try {

			Results<ScoredDocument> searchResults = INDEX.search(queryString);

			for (ScoredDocument scoredDoc : searchResults) {
				Field fieldId = scoredDoc.getOnlyField("id");
				if (fieldId == null || fieldId.getText() == null)
					continue;

				long speakersId = Long.parseLong(fieldId.getText());
				if (speakersId != -1) {
					Speakers a = getSpeakers(speakersId);
					returnList.add(a);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mgr.close();
		}

		return returnList;
	}

	@ApiMethod(httpMethod = "GET", name = "speakers.list", path = "speakers/list")
	@SuppressWarnings({ "cast", "unchecked" })
	public CollectionResponse<Speakers> listSpeakers(@Nullable @Named("cursor") String cursorString, @Nullable @Named("limit") Integer limit) throws Exception {

		PersistenceManager pm = null;
		Cursor cursor = null;
		List<Speakers> execute = null;
		try {
			pm = getPersistenceManager();
			Query query = pm.newQuery(Speakers.class);

			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				Map<String, Object> extensionMap = new HashMap<String, Object>();
				extensionMap.put(JDOCursorHelper.CURSOR_EXTENSION, cursor);
				query.setExtensions(extensionMap);
			}

			if (limit != null) {
				query.setRange(0, limit);
			}

			execute = (List<Speakers>) query.execute();
			cursor = JDOCursorHelper.getCursor(execute);

			if (cursor != null) {
				cursorString = cursor.toWebSafeString();
			} else {
				cursorString = "";
			}

			execute.size();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}

		return CollectionResponse.<Speakers> builder().setItems(execute).setNextPageToken(cursorString).build();
	}

	@ApiMethod(httpMethod = "GET", name = "speakers.get", path = "speakers/get/{id}")
	public Speakers getSpeakers(@Named("id") Long id) {
		PersistenceManager mgr = getPersistenceManager();
		Speakers speakers = null;
		try {
			speakers = mgr.getObjectById(Speakers.class, id);
		} finally {
			mgr.close();
		}
		return speakers;
	}

	public Speakers updateSpeakers(Speakers speakers) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			mgr.makePersistent(speakers);
		} finally {
			mgr.close();
		}
		return speakers;
	}

	public Speakers removeSpeakers(@Named("id") Long id) {
		PersistenceManager mgr = getPersistenceManager();
		Speakers speakers = null;
		try {
			speakers = mgr.getObjectById(Speakers.class, id);
			mgr.deletePersistent(speakers);
		} finally {
			mgr.close();
		}
		return speakers;
	}

	private static final Index INDEX = getIndex();

	private static Index getIndex() {
		IndexSpec indexSpec = IndexSpec.newBuilder().setName("speaker_index").setConsistency(Consistency.PER_DOCUMENT).build();
		return SearchServiceFactory.getSearchService().getIndex(indexSpec);
	}

	public static void addToSearchIndex(Speakers speakers) {
		Document.Builder docBuilder = Document.newBuilder().addField(Field.newBuilder().setName("id").setText(Long.toString(speakers.getId())))
				.addField(Field.newBuilder().setName("name").setText(speakers.getUserId() != null ? speakers.getUserId() : ""));
		docBuilder.setId(Long.toString(speakers.getId()));
		Document doc = docBuilder.build();
		INDEX.add(doc);
	}

	private static PersistenceManager getPersistenceManager() {
		return PMF.get().getPersistenceManager();
	}

}

package connection;

import java.util.List;

import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.*;

import models.Tag;

class FiltersDB {

	protected static Bson[] getRegexes(List<Tag> tags) {
		
		if(tags.size() == 1) {
			return getBson1(tags);
		}else if(tags.size() == 2) {
			return getBson2(tags);
		}else if(tags.size() == 3) {
			return getBson3(tags);
		}
		return null;
	}

	private static Bson[] getBson3(List<Tag> tags) {
		System.out.println(tags.get(0).toString());
		System.out.println(tags.get(1).toString());
		System.out.println(tags.get(2).toString());
		Bson[] b = {regex(tags.get(0).getType(),tags.get(0).getName(),"i"),
					regex(tags.get(1).getType(),tags.get(1).getName(),"i"),
					regex(tags.get(2).getType(),tags.get(2).getName(),"i")};
		return b;
	}

	private static Bson[] getBson2(List<Tag> tags) {
		System.out.println(tags.get(0).toString());
		System.out.println(tags.get(1).toString());
		Bson[] b = {regex(tags.get(0).getType(),tags.get(0).getName(),"i"),
					regex(tags.get(1).getType(),tags.get(1).getName(),"i")};
		return b;
	}

	private static Bson[] getBson1(List<Tag> tags) {
		System.out.println(tags.get(0).toString());
		Bson[] b = {regex(tags.get(0).getType(),tags.get(0).getName(),"i")};
		return b;
	}
}

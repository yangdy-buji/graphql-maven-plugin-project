/** Generated by the default template from graphql-java-generator */
package com.graphql_java_generator.client.domain.allGraphQLCases;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.graphql_java_generator.GraphQLField;
import com.graphql_java_generator.annotation.GraphQLInputParameters;
import com.graphql_java_generator.annotation.GraphQLNonScalar;
import com.graphql_java_generator.annotation.GraphQLObjectType;
import com.graphql_java_generator.annotation.GraphQLScalar;
import java.util.List;

/**
 *
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
@GraphQLObjectType("Droid")
public class Droid 
	implements Character, WithID, AnyCharacter{

	public Droid(){
		// No action
	}

	@JsonProperty("id")
	@GraphQLScalar(fieldName = "id", graphQLTypeSimpleName = "ID", javaClass = String.class)
	String id;


	@JsonProperty("name")
	@GraphQLInputParameters(names = {"uppercase"}, types = {"Boolean"}, mandatories = {false}, listDepths = {0}, itemsMandatory = {false})
	@GraphQLScalar(fieldName = "name", graphQLTypeSimpleName = "String", javaClass = String.class)
	String name;


	@JsonProperty("friends")
	@JsonDeserialize(using = CustomJacksonDeserializers.ListCharacter.class)
	@GraphQLNonScalar(fieldName = "friends", graphQLTypeSimpleName = "Character", javaClass = Character.class)
	List<Character> friends;


	@JsonProperty("appearsIn")
	@JsonDeserialize(using = CustomJacksonDeserializers.ListEpisode.class)
	@GraphQLScalar(fieldName = "appearsIn", graphQLTypeSimpleName = "Episode", javaClass = Episode.class)
	List<Episode> appearsIn;


	@JsonProperty("primaryFunction")
	@GraphQLScalar(fieldName = "primaryFunction", graphQLTypeSimpleName = "String", javaClass = String.class)
	String primaryFunction;


	@JsonProperty("__typename")
	@GraphQLScalar(fieldName = "__typename", graphQLTypeSimpleName = "String", javaClass = String.class)
	String __typename;



	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setFriends(List<Character> friends) {
		this.friends = friends;
	}

	public List<Character> getFriends() {
		return friends;
	}

	public void setAppearsIn(List<Episode> appearsIn) {
		this.appearsIn = appearsIn;
	}

	public List<Episode> getAppearsIn() {
		return appearsIn;
	}

	public void setPrimaryFunction(String primaryFunction) {
		this.primaryFunction = primaryFunction;
	}

	public String getPrimaryFunction() {
		return primaryFunction;
	}

	public void set__typename(String __typename) {
		this.__typename = __typename;
	}

	public String get__typename() {
		return __typename;
	}

    public String toString() {
        return "Droid {"
				+ "id: " + id
				+ ", "
				+ "name: " + name
				+ ", "
				+ "friends: " + friends
				+ ", "
				+ "appearsIn: " + appearsIn
				+ ", "
				+ "primaryFunction: " + primaryFunction
				+ ", "
				+ "__typename: " + __typename
        		+ "}";
    }

    /**
	 * Enum of field names
	 */
	 public static enum Field implements GraphQLField {
		Id("id"),
		Name("name"),
		Friends("friends"),
		AppearsIn("appearsIn"),
		PrimaryFunction("primaryFunction"),
		__typename("__typename");

		private String fieldName;

		Field(String fieldName) {
			this.fieldName = fieldName;
		}

		public String getFieldName() {
			return fieldName;
		}

		public Class<?> getGraphQLType() {
			return this.getClass().getDeclaringClass();
		}

	}

	public static Builder builder() {
			return new Builder();
		}



	/**
	 * Builder
	 */
	public static class Builder {
		private String id;
		private String name;
		private List<Character> friends;
		private List<Episode> appearsIn;
		private String primaryFunction;


		public Builder withId(String id) {
			this.id = id;
			return this;
		}
		public Builder withName(String name) {
			this.name = name;
			return this;
		}
		public Builder withFriends(List<Character> friends) {
			this.friends = friends;
			return this;
		}
		public Builder withAppearsIn(List<Episode> appearsIn) {
			this.appearsIn = appearsIn;
			return this;
		}
		public Builder withPrimaryFunction(String primaryFunction) {
			this.primaryFunction = primaryFunction;
			return this;
		}

		public Droid build() {
			Droid _object = new Droid();
			_object.setId(id);
			_object.setName(name);
			_object.setFriends(friends);
			_object.setAppearsIn(appearsIn);
			_object.setPrimaryFunction(primaryFunction);
			_object.set__typename("Droid");
			return _object;
		}
	}
}

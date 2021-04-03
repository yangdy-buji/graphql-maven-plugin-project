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
@GraphQLObjectType("__Field")
public class __Field 
{

	public __Field(){
		// No action
	}

	@JsonProperty("name")
	@GraphQLScalar(fieldName = "name", graphQLTypeSimpleName = "String", javaClass = String.class)
	String name;


	@JsonProperty("description")
	@GraphQLScalar(fieldName = "description", graphQLTypeSimpleName = "String", javaClass = String.class)
	String description;


	@JsonProperty("args")
	@JsonDeserialize(using = CustomJacksonDeserializers.List__InputValue.class)
	@GraphQLNonScalar(fieldName = "args", graphQLTypeSimpleName = "__InputValue", javaClass = __InputValue.class)
	List<__InputValue> args;


	@JsonProperty("type")
	@GraphQLNonScalar(fieldName = "type", graphQLTypeSimpleName = "__Type", javaClass = __Type.class)
	__Type type;


	@JsonProperty("isDeprecated")
	@GraphQLScalar(fieldName = "isDeprecated", graphQLTypeSimpleName = "Boolean", javaClass = Boolean.class)
	Boolean isDeprecated;


	@JsonProperty("deprecationReason")
	@GraphQLScalar(fieldName = "deprecationReason", graphQLTypeSimpleName = "String", javaClass = String.class)
	String deprecationReason;


	@JsonProperty("__typename")
	@GraphQLScalar(fieldName = "__typename", graphQLTypeSimpleName = "String", javaClass = String.class)
	String __typename;



	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setArgs(List<__InputValue> args) {
		this.args = args;
	}

	public List<__InputValue> getArgs() {
		return args;
	}

	public void setType(__Type type) {
		this.type = type;
	}

	public __Type getType() {
		return type;
	}

	public void setIsDeprecated(Boolean isDeprecated) {
		this.isDeprecated = isDeprecated;
	}

	public Boolean getIsDeprecated() {
		return isDeprecated;
	}

	public void setDeprecationReason(String deprecationReason) {
		this.deprecationReason = deprecationReason;
	}

	public String getDeprecationReason() {
		return deprecationReason;
	}

	public void set__typename(String __typename) {
		this.__typename = __typename;
	}

	public String get__typename() {
		return __typename;
	}

    public String toString() {
        return "__Field {"
				+ "name: " + name
				+ ", "
				+ "description: " + description
				+ ", "
				+ "args: " + args
				+ ", "
				+ "type: " + type
				+ ", "
				+ "isDeprecated: " + isDeprecated
				+ ", "
				+ "deprecationReason: " + deprecationReason
				+ ", "
				+ "__typename: " + __typename
        		+ "}";
    }

    /**
	 * Enum of field names
	 */
	 public static enum Field implements GraphQLField {
		Name("name"),
		Description("description"),
		Args("args"),
		Type("type"),
		IsDeprecated("isDeprecated"),
		DeprecationReason("deprecationReason"),
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
		private String name;
		private String description;
		private List<__InputValue> args;
		private __Type type;
		private Boolean isDeprecated;
		private String deprecationReason;


		public Builder withName(String name) {
			this.name = name;
			return this;
		}
		public Builder withDescription(String description) {
			this.description = description;
			return this;
		}
		public Builder withArgs(List<__InputValue> args) {
			this.args = args;
			return this;
		}
		public Builder withType(__Type type) {
			this.type = type;
			return this;
		}
		public Builder withIsDeprecated(Boolean isDeprecated) {
			this.isDeprecated = isDeprecated;
			return this;
		}
		public Builder withDeprecationReason(String deprecationReason) {
			this.deprecationReason = deprecationReason;
			return this;
		}

		public __Field build() {
			__Field _object = new __Field();
			_object.setName(name);
			_object.setDescription(description);
			_object.setArgs(args);
			_object.setType(type);
			_object.setIsDeprecated(isDeprecated);
			_object.setDeprecationReason(deprecationReason);
			_object.set__typename("__Field");
			return _object;
		}
	}
}

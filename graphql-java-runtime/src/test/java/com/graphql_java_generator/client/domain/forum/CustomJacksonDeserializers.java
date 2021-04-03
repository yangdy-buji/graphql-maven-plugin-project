/** Generated by the default template from graphql-java-generator */
package com.graphql_java_generator.client.domain.forum;

import java.util.List;

import com.graphql_java_generator.client.response.AbstractCustomJacksonDeserializer;

import graphql.schema.GraphQLScalarType;

/**
 * This class is a standard Deserializer for Jackson. It uses the {@link GraphQLScalarType} that is implemented by the
 * project for this scalar
 */
public class CustomJacksonDeserializers {

	public static class Date extends AbstractCustomJacksonDeserializer<java.util.Date> {
		private static final long serialVersionUID = 1L;

		public Date() {
			super(null, false, java.util.Date.class,
					com.graphql_java_generator.customscalars.GraphQLScalarTypeDate.Date);
		}
	}

	public static class List__Directive extends
			AbstractCustomJacksonDeserializer<List<com.graphql_java_generator.client.domain.forum.__Directive>> {
		private static final long serialVersionUID = 1L;

		public List__Directive() {
			super(null, true, com.graphql_java_generator.client.domain.forum.__Directive.class, null);
		}
	}

	public static class ListBoard
			extends AbstractCustomJacksonDeserializer<List<com.graphql_java_generator.client.domain.forum.Board>> {
		private static final long serialVersionUID = 1L;

		public ListBoard() {
			super(null, true, com.graphql_java_generator.client.domain.forum.Board.class, null);
		}
	}

	public static class List__DirectiveLocation extends
			AbstractCustomJacksonDeserializer<List<com.graphql_java_generator.client.domain.forum.__DirectiveLocation>> {
		private static final long serialVersionUID = 1L;

		public List__DirectiveLocation() {
			super(null, true, com.graphql_java_generator.client.domain.forum.__DirectiveLocation.class, null);
		}
	}

	public static class ListPost
			extends AbstractCustomJacksonDeserializer<List<com.graphql_java_generator.client.domain.forum.Post>> {
		private static final long serialVersionUID = 1L;

		public ListPost() {
			super(null, true, com.graphql_java_generator.client.domain.forum.Post.class, null);
		}
	}

	public static class List__InputValue extends
			AbstractCustomJacksonDeserializer<List<com.graphql_java_generator.client.domain.forum.__InputValue>> {
		private static final long serialVersionUID = 1L;

		public List__InputValue() {
			super(null, true, com.graphql_java_generator.client.domain.forum.__InputValue.class, null);
		}
	}

	public static class List__Field
			extends AbstractCustomJacksonDeserializer<List<com.graphql_java_generator.client.domain.forum.__Field>> {
		private static final long serialVersionUID = 1L;

		public List__Field() {
			super(null, true, com.graphql_java_generator.client.domain.forum.__Field.class, null);
		}
	}

	public static class List__Type
			extends AbstractCustomJacksonDeserializer<List<com.graphql_java_generator.client.domain.forum.__Type>> {
		private static final long serialVersionUID = 1L;

		public List__Type() {
			super(null, true, com.graphql_java_generator.client.domain.forum.__Type.class, null);
		}
	}

	public static class ListTopic
			extends AbstractCustomJacksonDeserializer<List<com.graphql_java_generator.client.domain.forum.Topic>> {
		private static final long serialVersionUID = 1L;

		public ListTopic() {
			super(null, true, com.graphql_java_generator.client.domain.forum.Topic.class, null);
		}
	}

	public static class List__EnumValue extends
			AbstractCustomJacksonDeserializer<List<com.graphql_java_generator.client.domain.forum.__EnumValue>> {
		private static final long serialVersionUID = 1L;

		public List__EnumValue() {
			super(null, true, com.graphql_java_generator.client.domain.forum.__EnumValue.class, null);
		}
	}

}

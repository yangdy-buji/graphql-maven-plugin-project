##
## Velocity template for the executor of a Subscription type (client side). 
##
## The generated class contains:
## - All the utility classes that allow to prepare and execute the subscription
##
##
## This template has these inputs:
## packageUtilName 			The package where this class must be generated
## configuration		The plugin's configuration
## object					The subscription type, for which this executor is being generated
##
/** Generated by the default template from graphql-java-generator */
package ${packageUtilName};
#macro(inputParams)#foreach ($inputParameter in $field.inputParameters), ${inputParameter.javaType} ${inputParameter.javaName}#end#end
#macro(inputValues)#foreach ($inputParameter in $field.inputParameters), ${inputParameter.javaName}#end#end

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql_java_generator.annotation.GraphQLNonScalar;
import com.graphql_java_generator.annotation.GraphQLScalar;
import com.graphql_java_generator.exception.GraphQLRequestExecutionException;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;
import com.graphql_java_generator.client.request.InputParameter;
import com.graphql_java_generator.client.request.ObjectResponse;

#foreach($import in ${object.importsForUtilityClasses})
import $import;
#end

import com.graphql_java_generator.client.GraphQLConfiguration;
import com.graphql_java_generator.client.GraphqlClientUtils;
import com.graphql_java_generator.client.SubscriptionCallback;
import com.graphql_java_generator.client.SubscriptionClient;

## When seperateUtilityClasses is set to true, the current class is generated in a util subpackage.
## So we need to import the object.classSimpleName
#if(${configuration.separateUtilityClasses})
// Utility classes are generated in the util subpackage. We need to import the ${object.classSimpleName} from the 'main' package
import ${configuration.packageName}.${object.classSimpleName};
#end


/**
 * This class contains the methods that allows the execution of the subscriptions that are defined in the ${object.name} of the GraphQL schema.<BR/>
 * These methods allows:
 * <UL>
 * <LI>Preparation of partial subscription requests</LI>
 * <LI>Execution of partial prepared subscription requests</LI>
 * <LI>Execution of partial direct subscription requests</LI>
 * </UL>
 * You'll find all the documentation on the <A HREF="https://graphql-maven-plugin-project.graphql-java-generator.com/client_subscription.html">subscription client page doc</A>.
 * 
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
@Component
public class ${object.classSimpleName}Executor {

	/** Logger for this class */
	private static Logger logger = LoggerFactory.getLogger(${object.name}Executor.class);

	GraphqlClientUtils graphqlClientUtils = new GraphqlClientUtils();

	@Autowired
	GraphQLConfiguration configuration = null;

	/**
	 * This default constructor is used by Spring, when building the component, and by the Jackson deserializer.
	 */
	@Autowired
	public ${object.classSimpleName}Executor() {
		CustomScalarRegistryInitializer.initCustomScalarRegistry();
		DirectiveRegistryInitializer.initDirectiveRegistry();
	}

	/**
	 * This constructor expects the URI of the GraphQL server. This constructor works only for http servers, not for
	 * https ones.<BR/>
	 * For example: http://my.server.com/graphql
	 * 
	 * @param graphqlEndpoint
	 *            the http URI for the GraphQL endpoint
	 */
	public ${object.classSimpleName}Executor(String graphqlEndpoint) {
		this.configuration = new GraphQLConfiguration(graphqlEndpoint);
		CustomScalarRegistryInitializer.initCustomScalarRegistry();
		DirectiveRegistryInitializer.initDirectiveRegistry();
	}

	/**
	 * This constructor expects the URI of the GraphQL server. This constructor works only for https servers, not for
	 * http ones.<BR/>
	 * For example: https://my.server.com/graphql<BR/><BR/>
	 * {@link SSLContext} and {@link HostnameVerifier} are regular Java stuff. You'll find lots of documentation on the web. 
	 * The StarWars sample is based on the <A HREF="http://www.thinkcode.se/blog/2019/01/27/a-jersey-client-supporting-https">http://www.thinkcode.se/blog/2019/01/27/a-jersey-client-supporting-https</A> blog.
	 * But this sample implements a noHostVerification, which of course, is the simplest but the safest way to go.
	 * 
	 * @param graphqlEndpoint
	 *            the https URI for the GraphQL endpoint
	 * @param sslContext
	 * @param hostnameVerifier
	 */
	public ${object.classSimpleName}Executor(String graphqlEndpoint, SSLContext sslContext, HostnameVerifier hostnameVerifier) {
		this.configuration = new GraphQLConfiguration(graphqlEndpoint, sslContext, hostnameVerifier);
		CustomScalarRegistryInitializer.initCustomScalarRegistry();
		DirectiveRegistryInitializer.initDirectiveRegistry();
	}

	/**
	 * This constructor expects the URI of the GraphQL server and a configured JAX-RS client
	 * that gives the opportunity to customise the REST request<BR/>
	 * For example: http://my.server.com/graphql
	 *
	 * @param graphqlEndpoint
	 *            the http URI for the GraphQL endpoint
	 * @param client
	 *            {@link Client} javax.ws.rs.client.Client to support customization of the rest request
	 * @param objectMapper
	 *            {@link ObjectMapper} com.fasterxml.jackson.databind.ObjectMapper to support configurable mapping
	 */
	public ${object.classSimpleName}Executor(String graphqlEndpoint, Client client, ObjectMapper objectMapper) {
		this.configuration = new GraphQLConfiguration(graphqlEndpoint, client, objectMapper);
		CustomScalarRegistryInitializer.initCustomScalarRegistry();
		DirectiveRegistryInitializer.initDirectiveRegistry();
	}
	
#foreach ($field in $object.fields)
	/**
	 * This method registers a subscription, by executing a direct partial request against the GraphQL server. This
	 * subscription is one of the fields defined in the GraphQL subscription object. The queryResponseDef contains the
	 * part of the subscription that <B><U>is after</U></B> the subscription name (see the sample below), for instance
	 * "{id name}" if you want these two fields to be sent in the notifications you'll receive for this
	 * subscription.<BR/>
	 * You must also provide a callback instance of the {@link SubscriptionCallback}, and the parameter for the
	 * subscription as parameter for this method. For instance, if the subscription subscribeToNewPost has one parameter
	 * <I>boardName</I> (as defined in the GraphQL schema):
	 * 
	 * <PRE>
	 * SubscriptionClient client;
	 * 
	 * void setup() {
	 * 	subscriptionType = new SubscriptionType("http://localhost:8180/graphql/subscription");
	 * }
	 * 
	 * void exec() {
	 * 	Map<String, Object> params = new HashMap<>();
	 * 	params.put("anOptionalParam", "a param value");
	 * 	// PostSubscriptionCallback implement SubscriptionCallback<Post>, as Post is the returned type for the
	 * 	// subscribeToNewPost subscription. Its onMessage(T) method will be called for each notification of this
	 * 	// subscription.
	 * 	client = subscriptionType.subscribeToNewPost(
	 * 			"{id date author publiclyAvailable title(param: ?anOptionalParam) content}",
	 * 			new PostSubscriptionCallback(), 
	 *          "Board name 1", // The parameter(s) of the subscription if any, are directly sent as parameter for this method
	 *          params // The bind variable you defined in your query are in this map.  
	 *          );
	 * }
	 * 
	 * void freeResources() {
	 * 	client.unsubscribe();
	 * }
	 * </PRE>
	 * 
	 * @param queryResponseDef
	 *            The response definition of the subscription, in the native GraphQL format (see here above)
	 * @param subscriptionCallback
	 *            An instance of SubscriptionCallback<${field.type.classSimpleName}>. Its {@link SubscriptionCallback#onMessage(Object)} 
	 *            will be called for each notification received from this subscription.
#foreach ($inputParameter in $field.inputParameters)
	 * @param ${inputParameter.name} Parameter for the ${field.name} field of ${object.name}, as defined in the GraphQL schema
#end
	 * @param parameters
	 *            The list of values, for the bind variables defined in the subscription. If there is no bind variable in the
	 *            defined subscription, this argument may be null or an empty {@link Map}
	 * @throws GraphQLRequestPreparationException
	 *             When an error occurs during the request preparation, typically when building the
	 *             {@link ObjectResponse}
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	public SubscriptionClient ${field.name}WithBindValues(String queryResponseDef, 
			SubscriptionCallback<${field.javaType}> subscriptionCallback
			#inputParams(), 
			Map<String, Object> parameters)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		logger.debug("Executing subscription '${field.name}': {} ", queryResponseDef);
		ObjectResponse objectResponse = get${field.pascalCaseName}ResponseBuilder().withQueryResponseDef(queryResponseDef).build();
		return ${field.javaName}(objectResponse, subscriptionCallback#inputValues(), parameters);
	}

	/**
	 * This method registers a subscription, by executing a direct partial request against the GraphQL server. This
	 * subscription is one of the fields defined in the GraphQL subscription object. The queryResponseDef contains the
	 * part of the subscription that <B><U>is after</U></B> the subscription name (see the sample below), for instance
	 * "{id name}" if you want these two fields to be sent in the notifications you'll receive for this
	 * subscription.<BR/>
	 * You must also provide a callback instance of the {@link SubscriptionCallback}, and the parameter for the
	 * subscription as parameter for this method. For instance, if the subscription subscribeToNewPost has one parameter
	 * <I>boardName</I> (as defined in the GraphQL schema):
	 * 
	 * <PRE>
	 * SubscriptionClient client;
	 * 
	 * void setup() {
	 * 	subscriptionType = new SubscriptionType("http://localhost:8180/graphql/subscription");
	 * }
	 * 
	 * void exec() {
	 * 	// PostSubscriptionCallback implement SubscriptionCallback<Post>, as Post is the returned type for the
	 * 	// subscribeToNewPost subscription. Its onMessage(T) method will be called for each notification of this
	 * 	// subscription.
	 * 	client = subscriptionType.subscribeToNewPost(
	 * 			"{id date author publiclyAvailable title(param: ?anOptionalParam) content}",
	 * 			new PostSubscriptionCallback(), 
	 *          "Board name 1", // The parameter(s) of the subscription if any, are directly sent as parameter for this method
	 *          "anOptionalParam", "a param value" // The bind variables that you've defined in your query are given as a listof couple of (name, value)  
	 *          );
	 * }
	 * 
	 * void freeResources() {
	 * 	client.unsubscribe();
	 * }
	 * </PRE>
	 * 
	 * @param queryResponseDef
	 *            The response definition of the subscription, in the native GraphQL format (see here above)
	 * @param subscriptionCallback
	 *            An instance of SubscriptionCallback<${field.type.classSimpleName}>. Its {@link SubscriptionCallback#onMessage(Object)} 
	 *            will be called for each notification received from this subscription.
#foreach ($inputParameter in $field.inputParameters)
	 * @param ${inputParameter.name} Parameter for the ${field.name} field of ${object.name}, as defined in the GraphQL schema
#end
	 * @param parameters
	 *            The list of values, for the bind variables defined in the subscription. If there is no bind variable in the
	 *            defined subscription, this argument may be null or an empty {@link Map}
	 * @throws GraphQLRequestPreparationException
	 *             When an error occurs during the request preparation, typically when building the
	 *             {@link ObjectResponse}
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	public SubscriptionClient ${field.name}(String queryResponseDef, 
			SubscriptionCallback<${field.javaType}> subscriptionCallback
			#inputParams(), 
			Object... paramsAndValues)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		logger.debug("Executing subscription '${field.name}' in query mode: {} ", queryResponseDef);
		ObjectResponse objectResponse = get${field.pascalCaseName}ResponseBuilder().withQueryResponseDef(queryResponseDef).build();
		return ${field.javaName}WithBindValues(objectResponse, subscriptionCallback#inputValues(), graphqlClientUtils.generatesBindVariableValuesMap(paramsAndValues));
	}

	/**
	 * This method registers a subscription, by executing a direct partial request against the GraphQL server. This
	 * subscription is one of the fields defined in the GraphQL subscription object. The queryResponseDef contains the
	 * part of the subscription that <B><U>is after</U></B> the subscription name (see the sample below), for instance
	 * "{id name}" if you want these two fields to be sent in the notifications you'll receive for this
	 * subscription.<BR/>
	 * You must also provide a callback instance of the {@link SubscriptionCallback}, and the parameter for the
	 * subscription as parameter for this method. For instance, if the subscription subscribeToNewPost has one parameter
	 * <I>boardName</I> (as defined in the GraphQL schema):
	 * 
	 * <PRE>
	 * SubscriptionClient client;
	 * GraphQLRequest subscriptionRequest;
	 * 
	 * void setup() {
	 * 	subscriptionType = new SubscriptionType("http://localhost:8180/graphql/subscription");
	 *  subscriptionRequest = subscriptionType
	 *			.getSubscribeToNewPostGraphQLRequest("{id date author publiclyAvailable title(param: ?anOptionalParam) content}");
	 * }
	 * 
	 * void exec() {
	 * 	Map<String, Object> params = new HashMap<>();
	 * 	params.put("anOptionalParam", "a param value");
	 * 	// PostSubscriptionCallback implement SubscriptionCallback<Post>, as Post is the returned type for the
	 * 	// subscribeToNewPost subscription. Its onMessage(T) method will be called for each notification of this
	 * 	// subscription.
	 * 	client = subscriptionType.subscribeToNewPost(
	 * 			subscriptionRequest,
	 * 			new PostSubscriptionCallback(), 
	 *          "Board name 1", // The parameter(s) of the subscription if any, are directly sent as parameter for this method
	 *          params // The bind variable you defined in your query are in this map.  
	 *          );
	 * }
	 * 
	 * void freeResources() {
	 * 	client.unsubscribe();
	 * }
	 * </PRE>
	 * 
	 * @param objectResponse
	 *            The definition of the response format, that describes what the GraphQL server is expected to return
	 * @param subscriptionCallback
	 *            An instance of SubscriptionCallback<${field.type.classSimpleName}>. Its {@link SubscriptionCallback#onMessage(Object)} 
	 *            will be called for each notification received from this subscription.
#foreach ($inputParameter in $field.inputParameters)
	 * @param ${inputParameter.name} Parameter for the ${field.name} field of ${object.name}, as defined in the GraphQL schema
#end
	 * @param parameters
	 *            The list of values, for the bind variables defined in the subscription. If there is no bind variable in the
	 *            defined Query, this argument may be null or an empty {@link Map}
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	public SubscriptionClient ${field.name}WithBindValues(ObjectResponse objectResponse,
			SubscriptionCallback<${field.javaType}> subscriptionCallback
			#inputParams(), 
			Map<String, Object> parameters)
			throws GraphQLRequestExecutionException  {
		if (logger.isTraceEnabled()) {
			logger.trace("Executing ${object.requestType} '${field.name}' with parameters: #foreach ($inputParameter in $field.inputParameters){}#if($foreach.hasNext),#end #end"#foreach ($inputParameter in $field.inputParameters), ${inputParameter.javaName}#end);
		} else if (logger.isDebugEnabled()) {
			logger.debug("Executing ${object.requestType} '${field.name}'");
		}
	
		// Given values for the BindVariables
		parameters = (parameters != null) ? parameters : new HashMap<>();
#foreach ($inputParameter in $field.inputParameters)
		parameters.put("${object.camelCaseName}${field.pascalCaseName}${inputParameter.pascalCaseName}", ${inputParameter.javaName});
#end

#if($field.fieldTypeAST.list)
		// This ugly double casting is necessary to make the code compile. If anyone has a better idea... please raise an issue
#end 
		return configuration.getQueryExecutor().execute(objectResponse, parameters,#if($field.fieldTypeAST.list) (SubscriptionCallback<List>) (Object)#end subscriptionCallback, "${field.name}", ${object.classSimpleName}.class, #if($field.fieldTypeAST.list)List#else${field.type.classSimpleName}#end.class);
	}

	/**
	 * This method registers a subscription, by executing a direct partial request against the GraphQL server. This
	 * subscription is one of the fields defined in the GraphQL subscription object. The queryResponseDef contains the
	 * part of the subscription that <B><U>is after</U></B> the subscription name (see the sample below), for instance
	 * "{id name}" if you want these two fields to be sent in the notifications you'll receive for this
	 * subscription.<BR/>
	 * You must also provide a callback instance of the {@link SubscriptionCallback}, and the parameter for the
	 * subscription as parameter for this method. For instance, if the subscription subscribeToNewPost has one parameter
	 * <I>boardName</I> (as defined in the GraphQL schema):
	 * 
	 * <PRE>
	 * SubscriptionClient client;
	 * GraphQLRequest subscriptionRequest;
	 * 
	 * void setup() {
	 * 	subscriptionType = new SubscriptionType("http://localhost:8180/graphql/subscription");
	 *  subscriptionRequest = subscriptionType
	 *			.getSubscribeToNewPostGraphQLRequest("{id date author publiclyAvailable title(param: ?anOptionalParam) content}");
	 * }
	 * 
	 * void exec() {
	 * 	Map<String, Object> params = new HashMap<>();
	 * 	params.put("anOptionalParam", "a param value");
	 * 	// PostSubscriptionCallback implement SubscriptionCallback<Post>, as Post is the returned type for the
	 * 	// subscribeToNewPost subscription. Its onMessage(T) method will be called for each notification of this
	 * 	// subscription.
	 * 	client = subscriptionType.subscribeToNewPost(
	 * 			subscriptionRequest,
	 * 			new PostSubscriptionCallback(), 
	 *          "Board name 1", // The parameter(s) of the subscription if any, are directly sent as parameter for this method
	 *          "anOptionalParam", "a param value" // The bind variables that you've defined in your query are given as a listof couple of (name, value)  
	 *          );
	 * }
	 * 
	 * void freeResources() {
	 * 	client.unsubscribe();
	 * }
	 * </PRE>
	 * 
	 * @param objectResponse
	 *            The definition of the response format, that describes what the GraphQL server is expected to return
	 * @param subscriptionCallback
	 *            An instance of SubscriptionCallback<${field.type.classSimpleName}>. Its {@link SubscriptionCallback#onMessage(Object)} 
	 *            will be called for each notification received from this subscription.
#foreach ($inputParameter in $field.inputParameters)
	 * @param ${inputParameter.name} Parameter for the ${field.name} field of ${object.name}, as defined in the GraphQL schema
#end
	 * @param paramsAndValues
	 *            This parameter contains all the name and values for the Bind Variables defined in the objectResponse
	 *            parameter, that must be sent to the server. Optional parameter may not have a value. They will be
	 *            ignored and not sent to the server. Mandatory parameter must be provided in this argument.<BR/>
	 *            This parameter contains an even number of parameters: it must be a series of name and values :
	 *            (paramName1, paramValue1, paramName2, paramValue2...)
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	public SubscriptionClient ${field.javaName}(ObjectResponse objectResponse,
			SubscriptionCallback<${field.javaType}> subscriptionCallback
			#inputParams(), 
			Object... paramsAndValues)
			throws GraphQLRequestExecutionException  {
		if (logger.isTraceEnabled()) {
			StringBuffer sb = new StringBuffer();
			sb.append("Executing subscription '${field.name}' with bind variables: ");
			boolean addComma = false;
			for (Object o : paramsAndValues) {
				if (o != null) {
					sb.append(o.toString());
					if (addComma)
						sb.append(", ");
					addComma = true;
				}
			}
			logger.trace(sb.toString());
		} else if (logger.isDebugEnabled()) {
			logger.debug("Executing subscription '${field.name}' (with bind variables)");
		}

		Map<String, Object> parameters = graphqlClientUtils.generatesBindVariableValuesMap(paramsAndValues);
#foreach ($inputParameter in $field.inputParameters)
		parameters.put("${object.camelCaseName}${field.pascalCaseName}${inputParameter.pascalCaseName}", ${inputParameter.javaName});
#end
		
#if($field.fieldTypeAST.list)
		// This ugly double casting is necessary to make the code compile. If anyone has a better idea... please raise an issue
#end 
		return configuration.getQueryExecutor().execute(objectResponse, parameters, #if($field.fieldTypeAST.list) (SubscriptionCallback<List>) (Object)#end subscriptionCallback, "${field.name}", ${object.classSimpleName}.class, #if($field.fieldTypeAST.list)List#else${field.type.classSimpleName}#end.class);
	}

	/**
	 * Get the {@link com.graphql_java_generator.client.request.Builder} for the ${field.type.classSimpleName}, as expected by the ${field.name} subscription.
	 * 
	 * @return
	 * @throws GraphQLRequestPreparationException
	 */
	public com.graphql_java_generator.client.request.Builder get${field.pascalCaseName}ResponseBuilder() throws GraphQLRequestPreparationException {
		return new com.graphql_java_generator.client.request.Builder(GraphQLRequest.class, "${field.name}", RequestType.${object.requestType}
#foreach ($inputParameter in $field.inputParameters)
			, InputParameter.newBindParameter("${inputParameter.name}","${object.camelCaseName}${field.pascalCaseName}${inputParameter.pascalCaseName}", ${inputParameter.fieldTypeAST.mandatory}, null)
#end
			);
	}


	/**
	 * Get the {@link GraphQLRequest} for the ${field.name} $type, created with the given Partial request.
	 * 
	 * @param partialRequest
	 * 				The Partial GraphQLRequest, as explained in the 
	 * 				<A HREF="https://graphql-maven-plugin-project.graphql-java-generator.com/client.html">plugin client documentation</A> 
	 * @return
	 * @throws GraphQLRequestPreparationException
	 */
	public GraphQLRequest get${field.pascalCaseName}GraphQLRequest(String partialRequest) throws GraphQLRequestPreparationException {
		GraphQLRequest ret = new GraphQLRequest(partialRequest, RequestType.${object.requestType}, "${field.name}"
#foreach ($inputParameter in $field.inputParameters)
		, InputParameter.newBindParameter("${inputParameter.name}","${object.camelCaseName}${field.pascalCaseName}${inputParameter.pascalCaseName}", ${inputParameter.fieldTypeAST.mandatory}, null)
#end
		);
		ret.setInstanceConfiguration(configuration);
		return ret;
	}
	
#end
}

/**
 * 
 */
package org.graphql.maven.plugin.samples.server.jpa;

import org.graphql.maven.plugin.samples.server.generated.Human;
import org.springframework.data.repository.CrudRepository;

/**
 * @author EtienneSF
 */
public interface HumanRepository extends CrudRepository<Human, String> {

}

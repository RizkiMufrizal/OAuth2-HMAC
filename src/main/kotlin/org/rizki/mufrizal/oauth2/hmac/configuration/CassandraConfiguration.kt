package org.rizki.mufrizal.oauth2.hmac.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cassandra.config.CassandraCqlClusterFactoryBean
import org.springframework.cassandra.config.DataCenterReplication
import org.springframework.cassandra.core.keyspace.CreateKeyspaceSpecification
import org.springframework.cassandra.core.keyspace.KeyspaceOption
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.data.cassandra.config.SchemaAction
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 03 December 2017
 * @Time 13.35
 * @Project oauth2-HMAC
 * @Package org.rizki.mufrizal.oauth2.hmac.configuration
 * @File CassandraConfiguration
 *
 */

@Configuration
@PropertySource("classpath:application.yml")
class CassandraConfiguration @Autowired constructor(val environment: Environment) : AbstractCassandraConfiguration() {

    override fun getKeyspaceName(): String {
        return environment.getRequiredProperty("spring.data.cassandra.keyspace-name")
    }

    override fun getSchemaAction(): SchemaAction {
        return SchemaAction.CREATE_IF_NOT_EXISTS
    }

    override fun getEntityBasePackages(): Array<String> {
        return arrayOf("org.rizki.mufrizal.oauth2.hmac.domain", "org.rizki.mufrizal.oauth2.hmac.domain.oauth2")
    }

    @Bean
    override fun cluster(): CassandraCqlClusterFactoryBean {
        val createKeyspaceSpecification = CreateKeyspaceSpecification
                .createKeyspace(environment.getRequiredProperty("spring.data.cassandra.keyspace-name"))
                .ifNotExists()
                .with(KeyspaceOption.DURABLE_WRITES)
                .withNetworkReplication(DataCenterReplication.dcr("datacenter1", 1))
        val cassandraCqlClusterFactoryBean = CassandraCqlClusterFactoryBean()
        cassandraCqlClusterFactoryBean.setClusterName(environment.getRequiredProperty("spring.data.cassandra.cluster-name"))
        cassandraCqlClusterFactoryBean.setContactPoints(environment.getRequiredProperty("spring.data.cassandra.contact-points"))
        cassandraCqlClusterFactoryBean.setPort(environment.getRequiredProperty("spring.data.cassandra.port").toInt())
        cassandraCqlClusterFactoryBean.setUsername(environment.getRequiredProperty("spring.data.cassandra.username"))
        cassandraCqlClusterFactoryBean.setPassword(environment.getRequiredProperty("spring.data.cassandra.password"))
        cassandraCqlClusterFactoryBean.keyspaceCreations = listOf(createKeyspaceSpecification)
        return cassandraCqlClusterFactoryBean
    }

}
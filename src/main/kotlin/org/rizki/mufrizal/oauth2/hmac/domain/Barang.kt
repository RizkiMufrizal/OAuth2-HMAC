package org.rizki.mufrizal.oauth2.hmac.domain

import com.datastax.driver.core.DataType
import org.springframework.data.cassandra.mapping.CassandraType
import org.springframework.data.cassandra.mapping.Column
import org.springframework.data.cassandra.mapping.PrimaryKey
import org.springframework.data.cassandra.mapping.Table
import java.util.UUID

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 03 December 2017
 * @Time 14.20
 * @Project oauth2-HMAC
 * @Package org.rizki.mufrizal.oauth2.hmac.domain
 * @File Barang
 *
 */

@Table(value = "tb_barang")
data class Barang(
        @PrimaryKey
        @CassandraType(type = DataType.Name.TIMEUUID)
        @Column(value = "id_barang")
        val idBarang: UUID? = null,
        @Column(value = "nama_barang")
        val namaBarang: String? = null,
        @CassandraType(type = DataType.Name.TEXT)
        @Column(value = "jenis_barang")
        val jenisBarang: JenisBarang? = null
)
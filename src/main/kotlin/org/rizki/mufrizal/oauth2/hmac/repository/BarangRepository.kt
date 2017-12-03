package org.rizki.mufrizal.oauth2.hmac.repository

import org.rizki.mufrizal.oauth2.hmac.domain.Barang
import org.springframework.data.repository.CrudRepository
import java.util.*

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 03 December 2017
 * @Time 15.30
 * @Project OAuth2-HMAC
 * @Package org.rizki.mufrizal.oauth2.hmac.repository
 * @File BarangRepository
 *
 */
interface BarangRepository : CrudRepository<Barang, UUID> {
    fun findByIdBarang(idBarang: UUID): Optional<Barang>
}
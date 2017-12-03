package org.rizki.mufrizal.oauth2.hmac.service

import org.rizki.mufrizal.oauth2.hmac.domain.Barang
import java.util.Optional

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 03 December 2017
 * @Time 15.38
 * @Project OAuth2-HMAC
 * @Package org.rizki.mufrizal.oauth2.hmac.service
 * @File BarangService
 *
 */
interface BarangService {
    fun getBarangs(): Iterable<Barang>
    fun saveBarang(barang: Barang): Barang
    fun updateBarang(barang: Barang): Barang
    fun deleteBarang(idBarang: String)
    fun getBarang(idBarang: String): Optional<Barang>
}
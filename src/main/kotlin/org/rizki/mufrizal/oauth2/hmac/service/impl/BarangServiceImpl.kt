package org.rizki.mufrizal.oauth2.hmac.service.impl

import org.rizki.mufrizal.oauth2.hmac.domain.Barang
import org.rizki.mufrizal.oauth2.hmac.repository.BarangRepository
import org.rizki.mufrizal.oauth2.hmac.service.BarangService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional
import java.util.UUID

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 03 December 2017
 * @Time 15.39
 * @Project OAuth2-HMAC
 * @Package org.rizki.mufrizal.oauth2.hmac.service.impl
 * @File BarangServiceImpl
 *
 */

@Service
class BarangServiceImpl @Autowired constructor(val barangRepository: BarangRepository) : BarangService {

    override fun getBarangs(): Iterable<Barang> {
        return barangRepository.findAll()
    }

    override fun saveBarang(barang: Barang): Barang {
        return barangRepository.save(barang)
    }

    override fun updateBarang(barang: Barang): Barang {
        return barangRepository.save(barang)
    }

    override fun deleteBarang(idBarang: String) {
        barangRepository.delete(UUID.fromString(idBarang))
    }

    override fun getBarang(idBarang: String): Optional<Barang> {
        return barangRepository.findByIdBarang(UUID.fromString(idBarang))
    }
}
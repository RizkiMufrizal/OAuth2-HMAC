package org.rizki.mufrizal.oauth2.hmac.controller

import org.rizki.mufrizal.oauth2.hmac.domain.Barang
import org.rizki.mufrizal.oauth2.hmac.helpers.HateoasResource
import org.rizki.mufrizal.oauth2.hmac.helpers.NotFoundRestHelper
import org.rizki.mufrizal.oauth2.hmac.helpers.ValidationIdHelper
import org.rizki.mufrizal.oauth2.hmac.service.BarangService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 03 December 2017
 * @Time 15.34
 * @Project OAuth2-HMAC
 * @Package org.rizki.mufrizal.oauth2.hmac.controller
 * @File BarangController
 *
 */
@RestController
@RequestMapping(value = ["/api"])
class BarangController @Autowired constructor(val barangService: BarangService) : ValidationIdHelper {

    @GetMapping(value = ["/barangs"])
    fun getBarangs(): ResponseEntity<*> {
        val hateoasResource = HateoasResource(this.barangService.getBarangs())
        hateoasResource.add(linkTo(methodOn(BarangController::class.java).getBarangs()).withSelfRel())
        return ResponseEntity(hateoasResource, HttpStatus.OK)
    }

    @GetMapping(value = ["/barangs/{idBarang}"])
    fun getBarang(@PathVariable("idBarang") idBarang: String): Barang {
        this.validateSelf(idBarang)
        return this.barangService.getBarang(idBarang).orElse(null)
    }

    override fun validateSelf(id: String) {
        this.barangService.getBarang(id).orElseThrow { NotFoundRestHelper(id = id, message = "Data Barang Tidak Tersedia") }
    }
}
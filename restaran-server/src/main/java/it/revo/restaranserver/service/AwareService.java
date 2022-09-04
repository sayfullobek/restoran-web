package it.revo.restaranserver.service;

import it.revo.restaranserver.entity.Aware;
import it.revo.restaranserver.payload.ApiResponse;
import it.revo.restaranserver.payload.AwareDto;
import it.revo.restaranserver.repository.AwareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AwareService {
    @Autowired
    AwareRepository awareRepository;

    public ApiResponse addAware(AwareDto awareDto) {
        boolean b = awareRepository.existsProductByNameUzEqualsIgnoreCaseAndNameEnEqualsIgnoreCaseAndNameRuEqualsIgnoreCase(awareDto.getNameUz(), awareDto.getNameEn(), awareDto.getNameRu());
        if (!b) {
            Aware aware = new Aware();
            aware.setNameUz(awareDto.getNameUz());
            aware.setNameEn(awareDto.getNameEn());
            aware.setNameRu(awareDto.getNameRu());
            aware.setLink(awareDto.getLink());
            aware.setAwareStatus(awareDto.getAwareStatus());
            awareRepository.save(aware);
            return new ApiResponse("successfully saved aware", true);
        }
        return new ApiResponse("bunday malumot bor", false);
    }

    public ApiResponse editAware(UUID id, AwareDto awareDto) {
        boolean b = awareRepository.existsProductByNameUzEqualsIgnoreCaseAndNameEnEqualsIgnoreCaseAndNameRuEqualsIgnoreCaseAndIdNot(awareDto.getNameUz(), awareDto.getNameEn(), awareDto.getNameRu(), id);
        if (!b) {
            Optional<Aware> byId = awareRepository.findById(id);
            if (byId.isPresent()) {
                Aware aware = byId.get();
                aware.setNameUz(awareDto.getNameUz());
                aware.setNameEn(awareDto.getNameEn());
                aware.setNameRu(awareDto.getNameRu());
                aware.setLink(awareDto.getLink());
                aware.setAwareStatus(awareDto.getAwareStatus());
                awareRepository.save(aware);
                return new ApiResponse("successfully saved aware", true);
            }
            return new ApiResponse("bunday aware mavjud emas", false);
        }
        return new ApiResponse("bunday malumot bor", false);
    }

    public ApiResponse deleteAware(UUID uuid) {
        Optional<Aware> byId = awareRepository.findById(uuid);
        if (byId.isPresent()) {
            Aware aware = byId.get();
            awareRepository.delete(aware);
            return new ApiResponse("successfully deleted aware", true);
        }
        return new ApiResponse("bunday aware mavjud emas", false);
    }
}

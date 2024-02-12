package ru.itmo.hls1.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.itmo.hls1.controllers.exceptions.not_found.NotFoundException;
import ru.itmo.hls1.controllers.exceptions.not_found.SportNotFoundException;
import ru.itmo.hls1.model.dto.SportDTO;
import ru.itmo.hls1.model.entity.Sport;
import ru.itmo.hls1.repository.SportRepository;
import ru.itmo.hls1.sevice.util.GeneralService;
import ru.itmo.hls1.sevice.util.Mapper;


@Service
@RequiredArgsConstructor
public class SportService extends GeneralService<Sport, SportDTO> {

    private final SportRepository sportRepository;
    private final Mapper<Sport, SportDTO> mapper = new SportMapper();

    public SportDTO updateSport(long id, SportDTO dto) {
        getEntityById(id);
        Sport updated = mapper.dtoToEntity(dto);
        updated.setSportId(id);
        sportRepository.save(updated);
        return mapper.entityToDto(updated);
    }

    @Override
    protected NotFoundException getNotFoundIdException(long id) {
        return new SportNotFoundException("id = " + id);
    }

    @Override
    protected Mapper<Sport, SportDTO> getMapper() {
        return mapper;
    }

    @Override
    protected JpaRepository<Sport, Long> getRepository() {
        return sportRepository;
    }

    static class SportMapper implements Mapper<Sport, SportDTO> {

        @Override
        public SportDTO entityToDto(Sport entity) {
            return new SportDTO(entity.getSportId(), entity.getSportName());
        }

        @Override
        public Sport dtoToEntity(SportDTO dto) {
            return new Sport(null, dto.getSportType());
        }
    }

}

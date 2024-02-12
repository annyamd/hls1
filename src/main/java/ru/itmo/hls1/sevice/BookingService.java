package ru.itmo.hls1.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.itmo.hls1.controllers.exceptions.not_found.NotFoundException;
import ru.itmo.hls1.model.dto.BookingDTO;
import ru.itmo.hls1.model.entity.Booking;
import ru.itmo.hls1.sevice.util.GeneralService;
import ru.itmo.hls1.sevice.util.Mapper;

@Service
@RequiredArgsConstructor
public class BookingService extends GeneralService<Booking, BookingDTO> {
    @Override
    protected NotFoundException getNotFoundIdException(long id) {
        return null;
    }

    @Override
    protected Mapper<Booking, BookingDTO> getMapper() {
        return null;
    }

    @Override
    protected JpaRepository<Booking, Long> getRepository() {
        return null;
    }
}

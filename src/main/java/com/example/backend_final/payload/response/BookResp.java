package com.example.backend_final.payload.response;

import com.example.backend_final.dto.BookDto;
import jakarta.persistence.Column;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BookResp {
    private List<BookDto> bookDtoList;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}

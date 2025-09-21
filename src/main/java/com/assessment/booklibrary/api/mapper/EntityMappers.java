package com.assessment.booklibrary.api.mapper;

import com.assessment.booklibrary.api.model.Book;
import com.assessment.booklibrary.api.model.BookDetail;
import com.assessment.booklibrary.api.model.BorrowRecord;
import com.assessment.booklibrary.api.model.Borrower;
import com.assessment.booklibrary.persistence.entity.BookEntity;
import com.assessment.booklibrary.persistence.entity.BorrowRecordEntity;
import com.assessment.booklibrary.persistence.entity.BorrowerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EntityMappers {
    BookDetail detailFromEntity(BookEntity bookEntity);

    Book fromEntity(BookEntity bookEntity);

    Borrower fromEntity(BorrowerEntity borrowerEntity);

    BorrowRecord fromEntity(BorrowRecordEntity borrowRecordEntity);
}

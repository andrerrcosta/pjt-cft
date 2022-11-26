package com.nobblecrafts.challenge.cft.mapper;

import com.nobblecrafts.challenge.cft.dto.Employee;
import com.nobblecrafts.challenge.cft.dto.Position;
import com.nobblecrafts.challenge.cft.dto.Selling;
import com.nobblecrafts.challenge.cft.entity.EmployeeEntity;
import com.nobblecrafts.challenge.cft.entity.PositionEntity;
import com.nobblecrafts.challenge.cft.entity.SellingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "default")
public abstract class DataMapper {

    public static final DataMapper INSTANCE = Mappers.getMapper(DataMapper.class);

    public abstract Position toRecord(PositionEntity entity);

    public abstract PositionEntity toEntity(Position record);

    public abstract List<Position> toPositionRecords(Iterable<PositionEntity> entity);

    public abstract List<PositionEntity> toPositionEntities(Iterable<Position> record);

    public abstract Selling toRecord(SellingEntity entity);

    public abstract SellingEntity toEntity(Selling record);

    public abstract List<Selling> toSelingRecords(Iterable<SellingEntity> entity);

    public abstract List<SellingEntity> toSellingEntities(Iterable<Selling> record);

    public abstract Employee toRecord(EmployeeEntity entity);

    public abstract EmployeeEntity toEntity(Employee record);

    public abstract List<Employee> toEmployeeRecords(Iterable<EmployeeEntity> entity);

    public abstract List<EmployeeEntity> toEmployeeEntities(Iterable<Employee> record);
}

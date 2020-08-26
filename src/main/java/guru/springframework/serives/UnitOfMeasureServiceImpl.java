package guru.springframework.serives;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitOfMeasureTOUnitOfMeasureCommand;
import guru.springframework.repository.UnitOfMeasureRepository;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService{
	
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private final UnitOfMeasureTOUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand; 
	
	public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository,
			UnitOfMeasureTOUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
	}




	@Override
	public Set<UnitOfMeasureCommand> listAlUoms() {
		return StreamSupport.stream(unitOfMeasureRepository.findAll().spliterator(), false)
				.map(unitOfMeasureToUnitOfMeasureCommand :: convert)
				.collect(Collectors.toSet());
	}

}

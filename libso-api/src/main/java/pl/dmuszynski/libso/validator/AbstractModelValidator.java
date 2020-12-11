package pl.dmuszynski.libso.validator;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pl.dmuszynski.libso.exception.IncorrectIdException;
import pl.dmuszynski.libso.model.AbstractEntity;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public abstract class AbstractModelValidator<T extends AbstractEntity> {

    private final CrudRepository<T, Long> repository;

    protected T validateWithReturnModelAndModelDtoIds(Long modelId, Long modelDtoId) {
        this.equalsModelAndModelDToIds(modelId, modelDtoId);
        return this.validateFindModelById(modelId);
    }

    public void validateModelAndModelDtoIds(Long modelId, Long modelDtoId) {
        this.equalsModelAndModelDToIds(modelId, modelDtoId);
        this.validateExistModelById(modelId);
    }

    public void validateExistModelById(Long modelId) throws ResourceNotFoundException {
        if (!this.repository.existsById(modelId))
            throw new ResourceNotFoundException("Model not found for this id: " + modelId);
    }

    protected T validateFindModelById(Long modelId) throws ResourceNotFoundException {
        return this.repository.findById(modelId)
            .orElseThrow(() -> new ResourceNotFoundException("Model not found for this id: " + modelId));
    }

    private void equalsModelAndModelDToIds(Long modelId, Long modelDtoId) throws IncorrectIdException {
        if (!modelId.equals(modelDtoId))
            throw new IncorrectIdException("You enter incorrect Model id: " +  modelId);
    }
}

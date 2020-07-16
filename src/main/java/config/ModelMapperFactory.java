package config;

import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;

public class ModelMapperFactory {
    @Produces
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}

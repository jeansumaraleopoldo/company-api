package com.company.sumpla.services.provider;

import com.company.sumpla.exceptions.NotFoundException;
import com.company.sumpla.model.Provider;
import com.company.sumpla.repositories.ProviderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProviderServiceImpl implements ProviderService{

    private final ProviderRepository providerRepository;

    public ProviderServiceImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public List<Provider> getProviders() {
        ArrayList<Provider> list = new ArrayList<>();
        providerRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Provider findById(Long id) {
        Optional<Provider> provider = providerRepository.findById(id);
        if(!provider.isPresent()){
            throw new NotFoundException("Entity Provider not found for ID: " + id);
        }
        return provider.get();
    }

    @Override
    public Provider saveProvider(Provider entity) {
        Provider provider = Provider.Builder.from(entity).build();
        return providerRepository.save(provider);
    }

    @Override
    public void deleteById(Long idProvider) {
        providerRepository.deleteById(idProvider);
    }
}

package com.company.sumpla.services.provider;

import com.company.sumpla.model.Provider;

import java.util.List;

public interface ProviderService {

    public List<Provider> getProviders();

    public Provider findById(Long id);

    public Provider saveProvider(Provider provider);

    public void deleteById(Long idProvider);
}

package com.vicmatskiv.weaponlib;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.function.UnaryOperator;

import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

public class TransformingResourceManager implements IResourceManager {

    private IResourceManager delegate;
    private UnaryOperator<ResourceLocation> locationTransformer;

    public TransformingResourceManager(IResourceManager delegate, UnaryOperator<ResourceLocation> locationTransformer) {
        this.delegate = delegate;
        this.locationTransformer = locationTransformer;
    }

    @Override
    public Set<String> getResourceDomains() {
        return delegate.getResourceDomains();
    }

    @Override
    public IResource getResource(ResourceLocation location) throws IOException {
        return delegate.getResource(locationTransformer.apply(location));
    }

    @Override
    public List<IResource> getAllResources(ResourceLocation location) throws IOException {
        return delegate.getAllResources(locationTransformer.apply(location));
    }

}

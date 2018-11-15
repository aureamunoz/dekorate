package io.ap4k;

import io.ap4k.config.Annotation;
import io.ap4k.config.AwsElasticBlockStoreVolume;
import io.ap4k.config.AzureDiskVolume;
import io.ap4k.config.AzureFileVolume;
import io.ap4k.config.ConfigMapVolume;
import io.ap4k.config.Env;
import io.ap4k.config.KubernetesConfig;
import io.ap4k.config.Label;
import io.ap4k.config.Mount;
import io.ap4k.config.PersistentVolumeClaimVolume;
import io.ap4k.config.Port;
import io.ap4k.config.SecretVolume;
import io.ap4k.visitor.AddAnnotation;
import io.ap4k.visitor.AddAwsElasticBlockStoreVolume;
import io.ap4k.visitor.AddAzureDiskVolume;
import io.ap4k.visitor.AddAzureFileVolume;
import io.ap4k.visitor.AddConfigMapVolume;
import io.ap4k.visitor.AddEnvVar;
import io.ap4k.visitor.AddLabel;
import io.ap4k.visitor.AddLivenessProbe;
import io.ap4k.visitor.AddMount;
import io.ap4k.config.visitors.AddPortToConfig;
import io.ap4k.visitor.AddPort;
import io.ap4k.visitor.AddPvcVolume;
import io.ap4k.visitor.AddReadinessProbe;
import io.ap4k.visitor.AddSecretVolume;
import io.ap4k.visitor.AddService;

/**
 * An abstract generator.
 * A generator is meant to popullate the initial resources to the {@link Session} as well as adding visitor etc.
 * @param <C>   The configuration type (its expected to vary between processors).
 */
public abstract class AbstractKubernetesGenerator<C extends KubernetesConfig> implements Generator<C> {

    protected final Resources resources;

    public AbstractKubernetesGenerator(Resources resources) {
        this.resources = resources;
    }

    /**
     * Generate / populate the resources.
     * @param config
     */
    public abstract void generate(C config);


    /**
     * Add all visitor to the resources.
     * This method will read the configuration and then add all the required visitor to the resources.
     * The method is intended to be called from the generate method and thus marked as protected.
     * @param config    The config.
     */
    protected void addVisitors(C config) {
        for (Label label : config.getLabels()) {
            resources.accept(new AddLabel(label));
        }
        for (Annotation annotation : config.getAnnotations()) {
            resources.accept(new AddAnnotation(annotation));
        }
        for (Env env : config.getEnvVars()) {
            resources.accept(new AddEnvVar(env));
        }
        for (Port port : config.getPorts()) {
            resources.accept(new AddPort(port));
        }
        for (Mount mount: config.getMounts()) {
            resources.accept(new AddMount(mount));
        }

        for (SecretVolume volume : config.getSecretVolumes()) {
            resources.accept(new AddSecretVolume(volume));
        }

        for (ConfigMapVolume volume : config.getConfigMapVolumes()) {
            resources.accept(new AddConfigMapVolume(volume));
        }

        for (PersistentVolumeClaimVolume volume : config.getPvcVolumes()) {
            resources.accept(new AddPvcVolume(volume));
        }

        for (AzureFileVolume volume : config.getAzureFileVolumes()) {
            resources.accept(new AddAzureFileVolume(volume));
        }

        for (AzureDiskVolume volume : config.getAzureDiskVolumes()) {
            resources.accept(new AddAzureDiskVolume(volume));
        }

        for (AwsElasticBlockStoreVolume volume : config.getAwsElasticBlockStoreVolumes()) {
            resources.accept(new AddAwsElasticBlockStoreVolume(volume));
        }

        resources.accept(new AddService(config));
        
        resources.accept(new AddLivenessProbe(config.getLivenessProbe()));
        resources.accept(new AddReadinessProbe(config.getReadinessProbe()));
    }
}

/**
 * Copyright 2018 The original authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **/
package io.ap4k.prometheus.model;

import io.ap4k.deps.kubernetes.api.KubernetesResourceMappingProvider;
import io.ap4k.deps.kubernetes.api.model.KubernetesResource;

import java.util.HashMap;
import java.util.Map;

public class ServiceMonitorMappingProvider implements KubernetesResourceMappingProvider {

  private final Map<String, Class<? extends KubernetesResource>> mappings = new HashMap<>();

  public ServiceMonitorMappingProvider() {
    mappings.put("monitoring.coreos.com/v1#ServiceMonitor", ServiceMonitor.class);
  }

  @Override
  public Map<String, Class<? extends KubernetesResource>> getMappings() {
    return mappings;
  }
}

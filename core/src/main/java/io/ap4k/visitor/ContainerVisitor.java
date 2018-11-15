/**
 * Copyright (C) 2018 Ioannis Canellos 
 *     
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
**/

package io.ap4k.visitor;

import io.fabric8.kubernetes.api.builder.TypedVisitor;
import io.fabric8.kubernetes.api.model.PodSpecBuilder;
import io.fabric8.kubernetes.api.model.Container;
import io.sundr.transform.annotations.VelocityTransformation;

@VelocityTransformation("/templates/fluent-visitor.vm")
public class ContainerVisitor implements FluentVisitor<Container, PodSpecBuilder> {

    public static ContainerFluentVisitor createNew() {
        return new ContainerFluentVisitor(v -> new TypedVisitor<PodSpecBuilder>() {
                @Override
                public void visit(PodSpecBuilder b) {
                    b.addToContainers(v);
                }
            });
    }

    public static ContainerFluentVisitor createNewInit() {
        return new ContainerFluentVisitor(v -> new TypedVisitor<PodSpecBuilder>() {
                @Override
                public void visit(PodSpecBuilder b) {
                    b.addToInitContainers(v);
                }
            });
    }
}

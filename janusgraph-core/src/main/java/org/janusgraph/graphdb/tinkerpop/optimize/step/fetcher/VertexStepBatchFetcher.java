// Copyright 2023 JanusGraph Authors
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.janusgraph.graphdb.tinkerpop.optimize.step.fetcher;

import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.janusgraph.core.JanusGraphElement;
import org.janusgraph.core.JanusGraphMultiVertexQuery;
import org.janusgraph.core.JanusGraphVertex;

import java.util.Map;

public class VertexStepBatchFetcher extends MultiQueriableStepBatchFetcher<Iterable<? extends JanusGraphElement>>{

    private final FetchQueryBuildFunction fetchQueryBuildFunction;

    private final Class<?> returnClass;

    public VertexStepBatchFetcher(FetchQueryBuildFunction fetchQueryBuildFunction, Class<?> returnClass) {
        this.fetchQueryBuildFunction = fetchQueryBuildFunction;
        this.returnClass = returnClass;
    }

    @Override
    protected Map<JanusGraphVertex, Iterable<? extends JanusGraphElement>> makeQueryAndExecute(JanusGraphMultiVertexQuery multiQuery) {
        multiQuery = fetchQueryBuildFunction.makeQuery(multiQuery);
        return Vertex.class.isAssignableFrom(returnClass) ? multiQuery.vertices() : multiQuery.edges();
    }
}

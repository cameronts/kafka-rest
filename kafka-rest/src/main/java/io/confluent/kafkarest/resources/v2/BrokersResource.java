/*
 * Copyright 2018 Confluent Inc.
 *
 * Licensed under the Confluent Community License (the "License"); you may not use
 * this file except in compliance with the License.  You may obtain a copy of the
 * License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.confluent.kafkarest.resources.v2;

import io.confluent.kafkarest.KafkaRestContext;
import io.confluent.kafkarest.Versions;
import io.confluent.kafkarest.entities.v2.BrokerList;
import io.confluent.rest.annotations.PerformanceMetric;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Resource representing the collection of all available brokers.
 */
@Path("/brokers")
@Produces(Versions.KAFKA_V2_JSON_WEIGHTED)
@Consumes()
public final class BrokersResource {

  private final KafkaRestContext ctx;

  BrokersResource(KafkaRestContext ctx) {
    this.ctx = ctx;
  }

  @GET
  @Valid
  @PerformanceMetric("brokers.list+v2")
  public BrokerList list() throws Exception {
    return new BrokerList(ctx.getAdminClientWrapper().getBrokerIds());
  }
}

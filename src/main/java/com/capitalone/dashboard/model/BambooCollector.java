/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.capitalone.dashboard.model;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Extension of Collector that stores current build server configuration.
 */
public class BambooCollector extends Collector {
  private List<String> buildServers = new ArrayList<>();
  private List<String> niceNames = new ArrayList<>();

  public List<String> getBuildServers() {
    return buildServers;
  }

  public List<String> getNiceNames() {
    return niceNames;
  }

  public void setNiceNames(List<String> niceNames) {
    this.niceNames = niceNames;
  }

  public void setBuildServers(List<String> buildServers) {
    this.buildServers = buildServers;
  }

  /**
   * Builds up a new BambooCollector based upon a {@link List} of build server names and a
   * {@link List} of {@code niceNames}.
   *
   * @param buildServers a {@link List} of {@link String}.
   * @param niceNames a {@link List} of {@link String}.
   * @return a new {@link BambooCollector}.
   */
  public static BambooCollector prototype(List<String> buildServers, List<String> niceNames) {
    BambooCollector protoType = new BambooCollector();
    protoType.setName("Bamboo");
    protoType.setCollectorType(CollectorType.Build);
    protoType.setOnline(true);
    protoType.setEnabled(true);
    protoType.getBuildServers().addAll(buildServers);
    if (!CollectionUtils.isEmpty(niceNames)) {
      protoType.getNiceNames().addAll(niceNames);
    }
    Map<String, Object> options = new HashMap<>();
    options.put(BambooJob.INSTANCE_URL, "");
    options.put(BambooJob.JOB_URL, "");
    options.put(BambooJob.JOB_NAME, "");
    protoType.setAllFields(options);
    protoType.setUniqueFields(options);
    return protoType;
  }
}

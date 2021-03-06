/*
 * Copyright 2014-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cloudfoundry.test.core;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Utility class for analysing a {@link ConnectionFactory}.
 */
@RestController
@RequestMapping("/rabbit")
public final class RabbitUtils extends AbstractServiceUtils<ConnectionFactory> {

    public RabbitUtils(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    public String checkAccess(ConnectionFactory connectionFactory) {
        try {
            connectionFactory.createConnection();
            return "ok";
        } catch (Exception e) {
            return "failed with " + e.getMessage();
        }
    }

    public String getUrl(ConnectionFactory connectionFactory) {
        return String.format("amqp://%s/%s", connectionFactory.getHost(), connectionFactory.getVirtualHost());
    }

}

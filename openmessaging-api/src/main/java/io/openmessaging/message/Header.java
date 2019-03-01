/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.openmessaging.message;

import io.openmessaging.KeyValue;
import io.openmessaging.extension.ExtensionHeader;

/**
 * The {@code Header} interface is the root interface of all OMS messages, and the most commonly used by OMS message
 * {@link Message}.
 * <p>
 * The header contains fields used by the messaging system that describes the message's meta information, while the body
 * contains the application data being transmitted.
 * <p>
 * As for the message header, OMS defines three kinds types: headers {@link Header} {@link ExtensionHeader} and
 * properties {@link KeyValue}, with respect to flexibility in vendor implementation and user usage.
 * <ul>
 * <li>
 * System Headers, OMS defines some standard attributes that represent the characteristics of the message.
 * </li>
 *
 * </ul>
 * The body contains the application data being transmitted, which is generally ignored by the messaging system and
 * simply transmitted to its destination.
 * <p>
 *
 * The header part is placed in the implementation classes of {@code Message}.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public interface Header {
    /**
     * The {@code DESTINATION} header field contains the destination to which the message is being sent.
     * <p>
     * When a message is set to the {@code Queue}, then the message will be sent to the specified destination.
     * <p>
     * When a message is received, its destination is equivalent to the {@code Queue} where the message resides in.
     */
    Header setDestination(String destination);

    /**
     * The {@code MESSAGE_ID} header field contains a value that uniquely identify each message sent by a {@code
     * Producer}. this identifier is generated by producer.
     */
    Header setMessageId(String messageId);

    /**
     * The {@code BORN_TIMESTAMP} header field contains the time a message was handed off to a {@code Producer} to be
     * sent.
     * <p>
     * When a message is sent, BORN_TIMESTAMP will be set with current timestamp as the born timestamp of a message in
     * client side, on return from the send method, the message's BORN_TIMESTAMP header field contains this value.
     * <p>
     * When a message is received its, BORN_TIMESTAMP header field contains this same value.
     * <p>
     * This filed is a {@code long} value, measured in milliseconds.
     */
    Header setBornTimestamp(long bornTimestamp);

    /**
     * The {@code BORN_HOST} header field contains the born host info of a message in client side.
     * <p>
     * When a message is sent, BORN_HOST will be set with the local host info, on return from the send method, the
     * message's BORN_HOST header field contains this value.
     * <p>
     * When a message is received, its BORN_HOST header field contains this same value.
     */
    Header setBornHost(String bornHost);

    /**
     * The {@code PRIORITY} header field contains the priority level of a message, a message with a higher priority
     * value should be delivered preferentially.
     * <p>
     * OMS defines a ten level priority value with 1 as the lowest priority and 10 as the highest, and the default
     * priority is 5. The priority beyond this region will be ignored.
     * <p>
     * OMS does not require or provide any guarantee that the message should be delivered in priority order strictly,
     * but the vendor should provide a best effort to deliver expedited messages ahead of normal messages.
     * <p>
     * If PRIORITY field isn't set explicitly, use {@code 5} as the default priority.
     */
    Header setPriority(short priority);

    /**
     * The {@code DURABILITY} header field contains the persistent level of a message, the vendor should guarantee the
     * reliability level for a message.
     * <p>
     * OMS defines two modes of message delivery:
     * <ul>
     * <li>
     * PERSISTENT, the persistent mode instructs the vendor should provide stable storage to ensure the message won't be
     * lost.
     * </li>
     * <li>
     * NON_PERSISTENT, this mode does not require the message be logged to stable storage, in most cases, the memory
     * storage is enough for better performance and lower cost.
     * </li>
     * </ul>
     */
    Header setDurability(short durability);

    /**
     * The {@code DELIVERY_COUNT} header field contains a number, which represents the count of the message delivery.
     */
    Header setDeliveryCount(int deliveryCount);

    /**
     * The field {@code COMPRESSION} in headers represents the message body compress algorithm. vendors are free to
     * choose the compression algorithm, but must ensure that the decompressed message is delivered to the user.
     */
    Header setCompression(short compression);

    /**
     * See {@link Header#setDestination(String)}
     *
     * @return destination
     */
    String getDestination();

    /**
     * See {@link Header#setMessageId(String)}
     *
     * @return messageId
     */
    String getMessageId();

    /**
     * See {@link Header#setBornTimestamp(long)}
     *
     * @return bornTimestamp
     */
    long getBornTimestamp();

    /**
     * See {@link Header#setBornHost(String)}
     *
     * @return bornHost
     */
    String getBornHost();

    /**
     * See {@link Header#setPriority(short)}
     *
     * @return priority
     */
    short getPriority();

    /**
     * See {@link Header#setDurability(short)}
     *
     * @return durability
     */
    short getDurability();

    /**
     * See {@link Header#setDeliveryCount(int)}
     *
     * @return deliveryCount
     */
    int getDeliveryCount();

    /**
     * See {@link Header#setCompression(short)}
     *
     * @return compression
     */
    short getCompression();
}

package com.playmoweb.iothub

import com.playmoweb.iothub.route.model.ListRoutesResponse
import org.junit.Test
import kotlin.test.assertEquals

class RouteSerializationTest {

    val json = IotHubSdk.DEFAULT_JSON

    /**
     * {"total_count":3,"routes":[{"id":"f6fe372c-0b84-44d7-b187-02b52dfb4f93","name":"staging-test-disconnected","hub_id":"e86c85f7-74be-416f-8d62-1e2f8c3e8850","topic":"test/1/0/+/disconnected","type":"rest","created_at":"2021-03-15T10:22:02.842Z","updated_at":"2021-05-10T14:25:21.635Z"},{"id":"ffd80b56-e86c-4019-b9d4-32589033da49","name":"staging-test-event-item","hub_id":"e86c85f7-74be-416f-8d62-1e2f8c3e8850","topic":"test/1/0/+/event/item","type":"rest","created_at":"2020-10-15T07:23:44.513Z","updated_at":"2020-10-15T07:23:44.513Z"},{"id":"5b2503aa-791f-4ca3-8b77-44cf4879b809","name":"staging-test-event-item","hub_id":"e86c85f7-74be-416f-8d62-1e2f8c3e8850","topic":"test/1/0/+/event/item","type":"rest","created_at":"2020-10-15T07:17:22.403Z","updated_at":"2020-10-15T07:17:22.403Z"},{"id":"1748838f-193a-4dba-a176-ede9599af89c","name":"staging-test-event-power","hub_id":"e86c85f7-74be-416f-8d62-1e2f8c3e8850","topic":"test/1/0/+/event/power","type":"rest","created_at":"2020-10-15T07:16:26.989Z","updated_at":"2020-10-15T07:16:26.989Z"},{"id":"1e564e7a-b618-4277-817a-b6c79b70f3cf","name":"staging-test-status","hub_id":"e86c85f7-74be-416f-8d62-1e2f8c3e8850","topic":"test/1/0/+/status","type":"rest","created_at":"2020-10-15T07:26:11.138Z","updated_at":"2020-10-15T07:26:11.138Z"}]}
     */
    @Test
    fun listDeserializationTest() {
        val jsonString = "{\"total_count\":3,\"routes\":[{\"id\":\"f6fe372c-0b84-44d7-b187-02b52dfb4f93\",\"name\":\"staging-test-disconnected\",\"hub_id\":\"e86c85f7-74be-416f-8d62-1e2f8c3e8850\",\"topic\":\"test/1/0/+/disconnected\",\"type\":\"rest\",\"created_at\":\"2021-03-15T10:22:02.842Z\",\"updated_at\":\"2021-05-10T14:25:21.635Z\"},{\"id\":\"ffd80b56-e86c-4019-b9d4-32589033da49\",\"name\":\"staging-test-event-item\",\"hub_id\":\"e86c85f7-74be-416f-8d62-1e2f8c3e8850\",\"topic\":\"test/1/0/+/event/item\",\"type\":\"rest\",\"created_at\":\"2020-10-15T07:23:44.513Z\",\"updated_at\":\"2020-10-15T07:23:44.513Z\"},{\"id\":\"5b2503aa-791f-4ca3-8b77-44cf4879b809\",\"name\":\"staging-test-event-item\",\"hub_id\":\"e86c85f7-74be-416f-8d62-1e2f8c3e8850\",\"topic\":\"test/1/0/+/event/item\",\"type\":\"rest\",\"created_at\":\"2020-10-15T07:17:22.403Z\",\"updated_at\":\"2020-10-15T07:17:22.403Z\"}]}"
        val listRouteResponse: ListRoutesResponse = json.decodeFromString(jsonString)
        println(listRouteResponse)
        assertEquals(3, listRouteResponse.totalCount)
        assertEquals(3, listRouteResponse.routes.size)
    }
}

/*
 * Copyright 2020 HM Revenue & Customs
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
 */

package models

import controllers.routes
import play.api.libs.json.JsObject
import play.api.libs.json.Json
import utils.CallOps._
import utils.Utils

import scala.xml.NodeSeq

object HateaosDepartureResponse {

  def apply(departureId: DepartureId, messageType: String, body: NodeSeq, locationValue: String): JsObject = {
    val messagesRoute = routes.DepartureTestMessagesController.injectEISResponse(departureId).urlWithContext

    Json.obj(
      "_links" -> Json.arr(
        Json.obj(
          "self"      -> Json.obj("href" -> s"$messagesRoute/${Utils.lastFragment(locationValue)}"),
          "departure" -> Json.obj("href" -> s"${Utils.dropLastFragment(messagesRoute)}")
        ),
        Json.obj(
          "id"          -> departureId.index.toString,
          "messageType" -> messageType,
          "body"        -> body.toString()
        )
      )
    )
  }
}

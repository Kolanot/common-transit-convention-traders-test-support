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

object HateaosArrivalResponse {

  def apply(arrivalId: ArrivalId, messageType: String, body: NodeSeq, locationValue: String): JsObject = {
    val messagesRoute = routes.ArrivalTestMessagesController.injectEISResponse(arrivalId).urlWithContext
    val messageId     = Utils.lastFragment(locationValue)

    Json.obj(
      "_links" -> Json.obj(
        "self"    -> Json.obj("href" -> s"$messagesRoute/$messageId"),
        "arrival" -> Json.obj("href" -> s"${Utils.dropLastFragment(messagesRoute)}")
      ),
      "arrivalId"   -> arrivalId.index.toString,
      "messageId"   -> messageId,
      "messageType" -> messageType,
      "body"        -> body.toString()
    )
  }
}

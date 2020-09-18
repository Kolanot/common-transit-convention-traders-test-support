///*
// * Copyright 2020 HM Revenue & Customs
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package connectors
//
//import config.AppConfig
//import config.Constants
//import javax.inject.Inject
//import models.ArrivalId
//import models.ItemId
//import play.api.mvc.RequestHeader
//import uk.gov.hmrc.http.HeaderCarrier
//import uk.gov.hmrc.http.HttpClient
//import uk.gov.hmrc.http.HttpResponse
//
//import scala.concurrent.ExecutionContext
//import scala.concurrent.Future
//
//class ArrivalConnector @Inject()(http: HttpClient, appConfig: AppConfig) extends BaseConnector(http) {
//
////  def post(messageType: String, message: String, arrivalId: ArrivalId)(implicit hc: HeaderCarrier, ec: ExecutionContext): Future[HttpResponse] = {
////    val eisPath = arrivalRoute format (arrivalId.index, Constants.MessageCorrelationId)
////
////    val url = appConfig.traderAtDestinationUrl + eisPath
////
////    val newHeaders = hc
////      .copy()
////      .withExtraHeaders(Seq("X-Message-Type" -> messageType): _*)
////
////    http.POSTString(url, message, requestHeaders)(CustomHttpReader, newHeaders, ec)
////  }
//
//  def post(messageType: String, message: String, arrivalId: ArrivalId)(implicit hc: HeaderCarrier, ec: ExecutionContext): Future[HttpResponse] = {
//    val eisPath = arrivalRoute format (arrivalId.index, Constants.MessageCorrelationId)
//
//    val url = appConfig.traderAtDestinationUrl + eisPath
//
//    super.post(messageType, message, arrivalId, eisPath, url)
//  }
//
//  def get(arrivalId: ItemId)(implicit requestHeader: RequestHeader, hc: HeaderCarrier, ec: ExecutionContext): Future[HttpResponse] =
//    super.get(arrivalId, appConfig.traderAtDestinationUrl + arrivalGetRoute)
//
////  def get(arrivalId: ArrivalId)(implicit requestHeader: RequestHeader, hc: HeaderCarrier, ec: ExecutionContext): Future[HttpResponse] = {
////    val url = appConfig.traderAtDestinationUrl + arrivalGetRoute + Utils.urlEncode(arrivalId.index.toString)
////
////    http.GET[HttpResponse](url, queryParams = Seq(), responseHeaders)(CustomHttpReader, enforceAuthHeaderCarrier(responseHeaders), ec)
////  }
//
//}

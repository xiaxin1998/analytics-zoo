/*
 * Copyright 2018 Analytics Zoo Authors.
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


package com.intel.analytics.zoo.pipeline.api.keras.objectives

import com.intel.analytics.bigdl.nn.AbsCriterion
import com.intel.analytics.bigdl.nn.abstractnn.AbstractCriterion
import com.intel.analytics.bigdl.nn.BCECriterion
import scala.reflect.ClassTag
import com.intel.analytics.bigdl.tensor.TensorNumericMath.TensorNumeric
import com.intel.analytics.bigdl.tensor.{DenseTensorApply, Tensor, TensorFunc4, TensorFunc6}

/**
 * This loss function measures the Binary Cross Entropy between the target and the output
 *         loss(o, t) = - 1/n sum_i (t[i] * log(o[i]) + (1 - t[i]) * log(1 - o[i]))
 * or in the case of the weights argument being specified:
 *         loss(o, t) = - 1/n sum_i weights[i] * (t[i] * log(o[i]) + (1 - t[i]) * log(1 - o[i]))
 *
 * By default, the losses are averaged for each mini-batch over observations as well as over
 * dimensions. However, if the field sizeAverage is set to false, the losses are instead summed.
 *
 * @param weights weights over the input dimension
 * @param sizeAverage avgerage or not in each mini-batch
 * @param ev numeric operator
 * @tparam T numeric type
 */
class BinaryCrossentropy[@specialized(Float, Double) T: ClassTag](
  val weights: Tensor[T] = null, sizeAverage: Boolean = true)
(implicit ev: TensorNumeric[T]) extends TensorLossFunction[T]{

  override val loss: AbstractCriterion[Tensor[T], Tensor[T], T] =
    BCECriterion[T](weights, sizeAverage)
}

object BinaryCrossentropy {
  def apply[@specialized(Float, Double) T: ClassTag](
      weights: Tensor[T] = null,
      sizeAverage: Boolean = true)(implicit ev: TensorNumeric[T]): BinaryCrossentropy[T] = {
    new BinaryCrossentropy[T](weights, sizeAverage)
  }
}
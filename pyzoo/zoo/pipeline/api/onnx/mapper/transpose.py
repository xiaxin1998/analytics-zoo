#
# Copyright 2018 Analytics Zoo Authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#


from zoo.pipeline.api.onnx.mapper.operator_mapper import OperatorMapper
from zoo.pipeline.api.onnx.onnx_helper import OnnxHelper
import zoo.pipeline.api.keras.layers as zlayers
import numpy as np


class TransposeMapper(OperatorMapper):
    def __init__(self, node, _params, _all_tensors):
        super(TransposeMapper, self).__init__(node, _params, _all_tensors)

    def _to_tensor(self):
        assert len(self.model_inputs) == 1, "Transpose accept single input only"
        dims = [int(i) for i in self.onnx_attr['perm']]
        for j in range(len(dims)):
            assert dims[j] != 0, "Not supported"
        transpose = zlayers.Permute(dims)
        return transpose
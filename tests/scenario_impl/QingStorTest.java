// +-------------------------------------------------------------------------
// | Copyright (C) 2016 Yunify, Inc.
// +-------------------------------------------------------------------------
// | Licensed under the Apache License, Version 2.0 (the "License");
// | you may not use this work except in compliance with the License.
// | You may obtain a copy of the License in the LICENSE file, or at:
// |
// | http://www.apache.org/licenses/LICENSE-2.0
// |
// | Unless required by applicable law or agreed to in writing, software
// | distributed under the License is distributed on an "AS IS" BASIS,
// | WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// | See the License for the specific language governing permissions and
// | limitations under the License.
// +-------------------------------------------------------------------------

package scenario_impl;

import com.qingstor.sdk.config.EvnContext;
import com.qingstor.sdk.exception.QSException;
import com.qingstor.sdk.service.QingStor;
import com.qingstor.sdk.service.QingStor.ListBucketsInput;
import com.qingstor.sdk.service.QingStor.ListBucketsOutput;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class QingStorTest {

    private EvnContext evnContext = TestUtil.getEvnContext();
    
    private QingStor storSerivce;
    
    private ListBucketsOutput listOutput;

    @When("^initialize QingStor service$")
    public void initialize_QingStor_service() throws Throwable {
        EvnContext evnContext = TestUtil.getEvnContext();
        storSerivce = new QingStor(evnContext, TestUtil.getZone());
        System.out.print("test : initService");
    }

    @Then("^the QingStor service is initialized$")
    public void initialized_QingStor_service() throws Throwable {
        if (this.storSerivce == null) {
            throw new QSException("not initialized");
        }
    }

    @When("^list buckets$")
    public void list_buckets() throws Throwable {
        EvnContext evnContext = TestUtil.getEvnContext();
        storSerivce = new QingStor(evnContext, TestUtil.getZone());
        this.listOutput = storSerivce.listBuckets(new ListBucketsInput());
    }

    @Then("^list buckets status code is (\\d+)$")
    public void list_buckets_status_code_is(int arg1) throws Throwable {
        TestUtil.assertNotNull(this.listOutput.getStatueCode());
        TestUtil.assertEqual(arg1, this.listOutput.getStatueCode());
    }


}


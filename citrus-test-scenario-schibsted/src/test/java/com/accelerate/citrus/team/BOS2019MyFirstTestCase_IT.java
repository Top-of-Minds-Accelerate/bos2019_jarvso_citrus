/*
 * Copyright 2006-2017 the original author or authors.
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

package com.accelerate.citrus.team;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.http.client.HttpClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

/**
 * @author Lenny
 */
@Test
public class BOS2019MyFirstTestCase_IT extends TestNGCitrusTestDesigner {

    /** Test Http REST client */
    @Autowired
    private HttpClient myFirstClient;

    @CitrusTest
    public void myFirstCitrusCall() {
    	
    	// ******************************************
    	// 1) Execute GET request... wait for response from TOP!!
    	// ******************************************
    	
    	// ****
    	// http://ws.audioscrobbler.com/2.0/?method=album.search&album=stock%20rocker&api_key=98b13da3d837aca8a6d776b6d630c689&format=json
    	// ****
    	
    	http().client(myFirstClient)
            .send()
            .get()
            .queryParam("method","album.search")
            .queryParam("album","stock rocker nuts")
            .queryParam("api_key","98b13da3d837aca8a6d776b6d630c689")
            .queryParam("format", "json")
            .queryParam("limit", "1")
            ;
                
        // Wait for response... Make sure to validate the status code (http -2??) 
    	http().client(myFirstClient)
            .receive()
            .response(HttpStatus.OK)
            ;

        echo("== I DID IT, SATOR ROCKS ==");  
		
    }
}

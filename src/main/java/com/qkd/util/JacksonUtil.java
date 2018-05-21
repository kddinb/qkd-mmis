/**
 * @(#)JacksonUtil.java 2016年9月7日
 *
 * Copyright ( c ) 2015 Wonders Information Co., Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Wonders 
 * Information Co., Ltd. ("Confidential Information").  You shall not disclose 
 * such Confidential Information and shall use it only in accordance with the 
 * terms of the license agreement you entered into with Wonders Information  
 * Co., Ltd. or a Wonders authorized reseller (the "License Agreement"). Wonders 
 *  may make changes to the Confidential Information from time to time. Such  
 * Confidential Information may contain errors.
 *
 * EXCEPT AS EXPLICITLY SET FORTH IN THE LICENSE AGREEMENT, WONDERS DISCLAIMS ALL
 * WARRANTIES, COVENANTS, REPRESENTATIONS, INDEMNITIES, AND GUARANTEES WITH
 * RESPECT TO SOFTWARE AND DOCUMENTATION, WHETHER EXPRESS OR IMPLIED, WRITTEN OR
 * ORAL, STATUTORY OR OTHERWISE INCLUDING, WITHOUT LIMITATION, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, TITLE, NON-INFRINGEMENT AND FITNESS FOR A
 * PARTICULAR PURPOSE. WONDERS DOES NOT WARRANT THAT END USER'S USE OF THE
 * SOFTWARE WILL BE UNINTERRUPTED, ERROR FREE OR SECURE.
 *
 * WONDERS SHALL NOT BE LIABLE TO END USER, OR ANY OTHER PERSON, CORPORATION OR
 * ENTITY FOR INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY OR CONSEQUENTIAL
 * DAMAGES, OR DAMAGES FOR LOSS OF PROFITS, REVENUE, DATA OR USE, WHETHER IN AN
 * ACTION IN CONTRACT, TORT OR OTHERWISE, EVEN IF WONDERS HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES. WONDERS' TOTAL LIABILITY TO END USER SHALL NOT
 * EXCEED THE AMOUNTS PAID FOR THE WONDERS SOFTWARE BY END USER DURING THE PRIOR
 * TWELVE (12) MONTHS FROM THE DATE IN WHICH THE CLAIM AROSE.  BECAUSE SOME
 * STATES OR JURISDICTIONS DO NOT ALLOW LIMITATION OR EXCLUSION OF CONSEQUENTIAL
 * OR INCIDENTAL DAMAGES, THE ABOVE LIMITATION MAY NOT APPLY TO END USER.
 *
 * Copyright version 1.0
 */
package com.qkd.util;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 提供bean转json格式或者json转bean格式
 */
public class JacksonUtil {
    private static ObjectMapper mapper = new ObjectMapper();
    
    public static String bean2Json(Object obj){
    	if (mapper == null) {
    		mapper = new ObjectMapper();
		}
		try {
			mapper.setSerializationInclusion(Include.NON_NULL);  
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }

    public static <T> T json2Bean(String jsonStr, Class<T> objClass){
        
    	try {
			return mapper.readValue(jsonStr, objClass);
			
    	}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	return null;
    	
    }
    
    /**
     * 
     * @Title: getNode 
     * @Description: 通过key获取jsonStr中node
     * @author: shenlx
     * @param jsonStr
     * @param key
     * @return
     * @return: String
     */
    public static String getNode(String jsonStr,String key){
       ObjectMapper mapper = new ObjectMapper();     
       JsonNode node;
	   try {
			node = mapper.readTree(jsonStr);
			return node.get(key).toString();
	   } catch (Exception e) {
			e.printStackTrace();
	   }
	   return null;
    }
}
/**
 * ﻿Copyright (C) 2012 52°North Initiative for Geospatial Open Source Software GmbH
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
package org.n52.sir.ds;

/**
 * @author Yakoub
 * 
 */
public interface IUserAccountDAO {

    // return null if the authentication fail , authToken otherwise
    public String authenticateUser(String name,
            String password);

    public String userNameForId(String id);

    public String getUserIDForUsername(String username);

    public String getUserIDForToken(String token);

    public boolean isAdmin(String user);

    public boolean isValid(String user);

    public boolean validate(String id);

    public boolean nameExists(String name);

    public boolean register(String name,
            String passwordHash);

}

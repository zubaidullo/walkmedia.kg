/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package controllers;


import java.io.IOException;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import ninja.Result;
import ninja.Results;
import ninja.params.Param;
import service.MailService;


@Singleton
public class ApplicationController
{
    @Inject
    MailService mailService;


    public Result index()
    {
        return Results.html();
    }


    private Result sendEmail( @Param( "name" ) String name, @Param( "email" ) String email,
                              @Param( "message" ) String message ) throws IOException
    {
        mailService.send( email, name, message, false );
        return Results.json().render( "Thanks!" );
    }
}

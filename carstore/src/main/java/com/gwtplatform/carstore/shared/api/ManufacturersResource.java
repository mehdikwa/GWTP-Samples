/**
 * Copyright 2013 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.gwtplatform.carstore.shared.api;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gwtplatform.carstore.shared.dto.ManufacturerDto;
import com.gwtplatform.carstore.shared.dto.ManufacturerRatingDto;

import static com.gwtplatform.carstore.shared.api.ApiParameters.ID;
import static com.gwtplatform.carstore.shared.api.ApiPaths.MANUFACTURER;
import static com.gwtplatform.carstore.shared.api.ApiPaths.PATH_ID;

@Path(MANUFACTURER)
@Produces(MediaType.APPLICATION_JSON)
public interface ManufacturersResource {
    @GET
    List<ManufacturerDto> getManufacturers();

    @GET
    @Path(PATH_ID)
    ManufacturerDto get(@PathParam(ID) Long id);

    @POST
    ManufacturerDto saveOrCreate(ManufacturerDto manufacturerDto);

    @DELETE
    @Path(PATH_ID)
    void delete(@PathParam(ID) Long id);

    @GET
    @Path(ApiPaths.RATING)
    List<ManufacturerRatingDto> getAverageRatings();
}

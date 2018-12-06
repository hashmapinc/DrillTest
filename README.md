<img src="https://s3.us-east-2.amazonaws.com/hm-witsml-server/drillFlowLogo.png" alt="DrillFlow"/>

A REST-based implementation for E&P Drilling data for testing Drillflow function

## Table of Contents
-   [Features](#features)
-   [Requirements](#requirements)
-   [Getting Started](#getting-started)
-   [Getting Help](#getting-help)
-   [Documentation](#documentation)
-   [License](#license)
-   [Export Control](#export-control)

## Features

-   Exposes data stored in an embedded H2 database for Well, Wellbore

## Requirements

-   JDK 11 
-   Apache Maven 3.3 or higher
-   Git Client
-   Docker (To build the docker image)

## Getting Started

### Building

Execute:

```bash
mvn clean install
```

### Running

Execute:

```bash
java -jar target/DrillTest-0.0.1-SNAPSHOT.jar
```

### Testing

By default the service will be available at:

`http://localhost:8080/`

### Building the Docker image

Navigate to the docker directory

Execute `docker build . -t hashmapinc/drilltest:latest` to build the image

Once completed execute `docker run -p 8080:8080 hashmapinc/drilltest:latest` 

## Getting Help
You can also submit issues or questions via GitHub Issues [here](https://github.com/hashmapinc/DrillTest/issues)

## Documentation

Currently only the Well API and Wellbore apis are supported

The proposed capabilities endpoint is also supported.

The Swagger endpoint is at [here](http://localhost:8080/swagger-ui.html)

## License

Except as otherwise noted this software is licensed under the
[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  [http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2Fhashmapinc%2FDrillflow.svg?type=large)](https://app.fossa.io/projects/git%2Bgithub.com%2Fhashmapinc%2FDrillflow?ref=badge_large)

## Export Control

This distribution includes cryptographic software. The country in which you
currently reside may have restrictions on the import, possession, use, and/or
re-export to another country, of encryption software. BEFORE using any
encryption software, please check your country's laws, regulations and
policies concerning the import, possession, or use, and re-export of encryption
software, to see if this is permitted. See <http://www.wassenaar.org/> for more
information.

The U.S. Government Department of Commerce, Bureau of Industry and Security
(BIS), has classified this software as Export Commodity Control Number (ECCN)
5D002.C.1, which includes information security software using or performing
cryptographic functions with asymmetric algorithms. The form and manner of this
distribution makes it eligible for export under the
License Exception ENC Technology Software Unrestricted (TSU) exception (see the
BIS Export Administration Regulations, Section 740.13) for both object code and
source code.

The following provides more details on the included cryptographic software:

This project uses BouncyCastle and the built-in
java cryptography libraries for SSL, SSH via CXF. See
[http://bouncycastle.org/about.html](http://bouncycastle.org/about.html)
[http://www.oracle.com/us/products/export/export-regulations-345813.html](http://www.oracle.com/us/products/export/export-regulations-345813.html)
for more details on each of these libraries cryptography features.

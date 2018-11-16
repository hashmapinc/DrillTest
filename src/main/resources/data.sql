insert into well (id,company,time_zone,streaming_state,live_state,uuid,name,data)
values(10001,'Hashmap','-6:00','Streaming','Connected','c6d249d2-12bc-47a3-91fd-0baa0e792259','6507/7-A-42','<?xml version="1.0" encoding="UTF-8"?>
<well xmlns="http://www.energistics.org/energyml/data/witsmlv2"
      xmlns:eml="http://www.energistics.org/energyml/data/commonv2"
      xmlns:gco="http://www.isotc211.org/2005/gco"
      xmlns:gmd="http://www.isotc211.org/2005/gmd"
      xmlns:gsr="http://www.isotc211.org/2005/gsr"
      xmlns:gts="http://www.isotc211.org/2005/gts"
      xmlns:gml="http://www.opengis.net/gml/3.2"
      xmlns:xlink="http://www.w3.org/1999/xlink"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      schemaVersion="2.0"
      uuid="c6d249d2-12bc-47a3-91fd-0baa0e792259">
   <eml:Aliases authority="1.4.1.1">
      <eml:Identifier>w-12</eml:Identifier>
      <eml:Description>Identifier is created from the uid of the input scheme</eml:Description>
   </eml:Aliases>
   <eml:Citation>
      <eml:Title>6507/7-A-42</eml:Title>
      <eml:Originator>1411_input</eml:Originator>
      <eml:Creation>2018-01-23T08:19:21.668-06:00</eml:Creation>
      <eml:Format>Mapforce</eml:Format>
      <eml:LastUpdate>2001-05-31T08:15:00Z</eml:LastUpdate>
   </eml:Citation>
   <NameLegal>Company Legal Name</NameLegal>
   <NumLicense>Company License Number</NumLicense>
   <NumGovt>Govt-Number</NumGovt>
   <DTimLicense>2001-05-15T13:20:00Z</DTimLicense>
   <Field>Big Field</Field>
   <Country>US</Country>
   <State>TX</State>
   <County>Montgomery</County>
   <Region>Region Name</Region>
   <District>District Name</District>
   <Block>Block Name</Block>
   <TimeZone>-06:00</TimeZone>
   <Operator>Operating Company</Operator>
   <OperatorDiv>Division Name</OperatorDiv>
   <PcInterest uom="%">65</PcInterest>
   <NumAPI>123-543-987AZ</NumAPI>
   <StatusWell>drilling</StatusWell>
   <PurposeWell>exploration</PurposeWell>
   <DTimSpud>2001-05-31T08:15:00Z</DTimSpud>
   <DTimPa>2001-07-15T15:30:00Z</DTimPa>
   <WaterDepth uom="ft">520</WaterDepth>
   <GeographicLocationWGS84 uid="loc-1">
      <Description>Location of well surface point in projected system.</Description>
      <Latitude uom="dega">0</Latitude>
      <Longitude uom="dega">0</Longitude>
      <Crs xsi:type="eml:GeodeticEpsgCrs">
         <eml:EpsgCode>4326</eml:EpsgCode>
      </Crs>
   </GeographicLocationWGS84>
   <ReferencePoint uid="SRP1">
      <Name>Slot Bay Centre</Name>
      <Type>Site Reference Point</Type>
      <Location xsi:type="ProjectedWellLocation" uid="proj1">
         <Description>Location of well surface point in projected system.</Description>
         <Coordinate1>0</Coordinate1>
         <Coordinate2>0</Coordinate2>
         <Crs xsi:type="eml:ProjectedLocalAuthorityCrs">
            <eml:LocalAuthorityCrsName authority="23031" code="23031">ED50 / UTM Zone 31N</eml:LocalAuthorityCrsName>
         </Crs>
      </Location>
   </ReferencePoint>
   <ReferencePoint uid="WRP2">
      <Name>Sea Bed</Name>
      <Type>well Reference Point</Type>
      <MeasuredDepth uom="ft" datum="KB">173.09</MeasuredDepth>
      <Elevation uom="ft" datum="SL">-118.40</Elevation>
      <Location xsi:type="GeodeticWellLocation" uid="loc-2">
         <Latitude uom="dega">59.743844</Latitude>
         <Longitude uom="dega">1.67198083</Longitude>
         <Crs xsi:type="eml:GeodeticLocalAuthorityCrs">
            <eml:LocalAuthorityCrsName authority="4230" code="5106">4230</eml:LocalAuthorityCrsName>
         </Crs>
      </Location>
      <Location xsi:type="ProjectedWellLocation" uid="proj1">
         <Description>Location of well surface point in projected system.</Description>
         <Coordinate1>0</Coordinate1>
         <Coordinate2>0</Coordinate2>
         <Crs xsi:type="eml:ProjectedLocalAuthorityCrs">
            <eml:LocalAuthorityCrsName authority="23031" code="23031">ED50 / UTM Zone 31N</eml:LocalAuthorityCrsName>
         </Crs>
      </Location>
   </ReferencePoint>
   <WellheadElevation uom="ft" datum="unknown">500</WellheadElevation>
   <WellDatum uid="KB">
      <Name>Kelly Bushing</Name>
      <Code>kelly bushing</Code>
      <Elevation uom="ft" datum="SL">78.5</Elevation>
      <Crs xsi:type="eml:VerticalUnknownCrs">
         <eml:Unknown>unknown</eml:Unknown>
      </Crs>
   </WellDatum>
   <WellDatum uid="SL">
      <Name>Sea Level</Name>
      <Code>mean sea level</Code>
      <Crs xsi:type="eml:VerticalUnknownCrs">
         <eml:Unknown>unknown</eml:Unknown>
      </Crs>
   </WellDatum>
   <GroundElevation uom="ft" datum="unknown">250</GroundElevation>
</well>
');

insert into well (id,company,time_zone,streaming_state,live_state,uuid,name,data)
values(10002,'Hashmap','+1:00','Streaming','Connected','e03e86c7-72c1-414e-aecc-8ea3c4','EnergisticsWell2016-E','<Well xmlns="http://www.energistics.org/energyml/data/witsmlv2"
      xmlns:eml="http://www.energistics.org/energyml/data/commonv2"
      xmlns:gco="http://www.isotc211.org/2005/gco"
      xmlns:gmd="http://www.isotc211.org/2005/gmd"
      xmlns:gsr="http://www.isotc211.org/2005/gsr"
      xmlns:gts="http://www.isotc211.org/2005/gts"
      xmlns:gml="http://www.opengis.net/gml/3.2"
      xmlns:xlink="http://www.w3.org/1999/xlink"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://www.energistics.org/energyml/data/witsmlv2 file:///C:/energyml/data/witsml/v2.0/xsd_schemas/Well.xsd"
      schemaVersion="2.0"
      uuid="e03e86c7-72c1-414e-aecc-8ea3c4">
   <eml:Aliases authority="1.4.1.1">
      <eml:Identifier>e03e86c7-72c1-414e-aecc-8ea3c4</eml:Identifier>
      <eml:Description>Identifier is created from the uid of the input scheme</eml:Description>
   </eml:Aliases>
   <eml:Citation>
      <eml:Title>EnergisticsWell2016-E</eml:Title>
      <eml:Originator>1411_input</eml:Originator>
      <eml:Creation>2018-11-16T14:21:24.937Z</eml:Creation>
      <eml:Format>Mapforce</eml:Format>
      <eml:LastUpdate>2016-04-05T11:33:43.755Z</eml:LastUpdate>
   </eml:Citation>
   <TimeZone>+01:00</TimeZone>
   <StatusWell>drilling</StatusWell>
   <PurposeWell>unknown</PurposeWell>
   <WellDatum uid="SL">
      <Name>Mean Sea Level</Name>
      <Code>mean sea level</Code>
      <Crs xsi:type="eml:VerticalUnknownCrs">
         <eml:Unknown>unknown</eml:Unknown>
      </Crs>
   </WellDatum>
</Well>
');


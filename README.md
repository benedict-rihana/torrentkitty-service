# Torrent Kitty Service

## Architect

This is a typical SpringBoot Application. 

```mermaid
graph LR;

request(Request) -->controller(Controller) --> service(Service) -->client(Client)
```

### Controller

The controller class will provide restful API to the external calling. It will be implemented using Spring.

### Service

The controller will call the service class to do the actual handling of the call.

### Client

The wrapper of http client implementations. It will request torrentkitty website and extract the useful imformation from
the http response


## dependencies

## TODO

1. The inside exception is not handled, a class is needed to track the exceptions or errors.
2. Build & Installation
3. Unit Testing Code
4. Url should be configured outside the jar package, `@PropertySource("file:${config.home}/config.properties")`
5. Search result paging, need to return an indicator if there is a next page.
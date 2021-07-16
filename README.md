NPS StrideUp
***

This is a very simple application that retrieves the information about parks from the National Park Service

**How to install and run the application**

- Fetch the project into your local environment
- Import the project to any IDE of your preference
- Run the CodingexerciseApplication.java class


How did you decide which technologies to use as part of your solution?

I decided to build the application on SpringBoot, because it is the JAVA framework I am most familiar with, also it is very easy to set up.
I also decided to use Spring Data JPA on the repository layer since it is very simple and reduces the amount of boilerplate code that needs to be written by just implementing the interface.

Are there any improvements you could make to your submission?

I would better refactor the controller by eliminating some duplicated code. I would also increment the testing coverage. I would also fix a current bug that happens if only the parkCode or the stateCode is duplicated, that doesn't mean the whole abject is duplicated but it is something that needs to be fixed, since it will throw an exception when querying by parkCode or stateCode. (only if they are duplicated)

What would you do differently if you were allocated more time?

In addition to the improvements mentioned above, I would improve the DB design.

API SPEC

```json
{
  "swagger": "2.0",
  "info": {
    "description": "This is a very simple API that allows to retrieve parks information from the National Parks Service",
    "version": "1.0.0",
    "title": "NPS StrideUp"
  },
  "basePath": "/api",
  "tags": [
    {
      "name": "parks",
      "description": "Information about all parks"
    }
  ],
  "schemes": [
    "https",
    "http"
  ],
  "paths": {
    "/parks": {
      "get": {
        "tags": [
          "park"
        ],
        "summary": "Add a new park information",
        "description": "Add information about a new park",
        "operationId": "getParks",
        "consumes": [
          "application/json"
        ],
        "produces": [
          "application/json"
        ],
        "responses": {
          "405": {
            "description": "Invalid input"
          }
        }
      }
    },
    "/parks/": {
      "post": {
        "tags": [
          "park"
        ],
        "summary": "Add a new park information",
        "description": "Add information about a new park",
        "operationId": "addPark",
        "consumes": [
          "application/json"
        ],
        "produces": [
          "application/json"
        ],
        "parameters": [
          {
            "in": "body",
            "name": "body",
            "description": "Park object that will be added",
            "required": true,
            "schema": {
              "$ref": "#/definitions/Park"
            }
          }
        ],
        "responses": {
          "405": {
            "description": "Invalid input"
          }
        }
      }
    },
    "/park/parkCode": {
      "get": {
        "tags": [
          "park"
        ],
        "summary": "Finds Parks by park code",
        "description": "The park code must be provided",
        "operationId": "getParkByParkCode",
        "produces": [
          "application/json"
        ],
        "parameters": [
          {
            "name": "parkCode",
            "in": "query",
            "description": "Park code that needs to be considered for filter",
            "required": true,
            "type": "string"
          }
        ],
        "responses": {
          "200": {
            "description": "successful operation"
          },
          "400": {
            "description": "Invalid status value"
          }
        }
      },
      "put": {
        "tags": [
          "parks"
        ],
        "summary": "Update an existing park",
        "description": "",
        "operationId": "updatePark",
        "consumes": [
          "application/json"
        ],
        "produces": [
          "application/json"
        ],
        "parameters": [
          {
            "in": "query",
            "name": "parkCode",
            "description": "Park object that will be updated according to the park code",
            "required": true,
            "type": "string"
          }
        ],
        "responses": {
          "400": {
            "description": "Invalid ID supplied"
          },
          "404": {
            "description": "Pet not found"
          },
          "405": {
            "description": "Validation exception"
          }
        }
      }
    }
  },
  "definitions": {
    "Park": {
      "type": "object",
      "properties": {
        "id": {
          "type": "number"
        },
        "description": {
          "type": "string"
        },
        "designation": {
          "type": "string"
        },
        "directionsInfo": {
          "type": "string"
        },
        "directionsUrl": {
          "type": "string"
        },
        "fullName": {
          "type": "string"
        },
        "latLong": {
          "type": "string"
        },
        "latitude": {
          "type": "string"
        },
        "longitude": {
          "type": "string"
        },
        "name": {
          "type": "string"
        },
        "parkCode": {
          "type": "string"
        },
        "states": {
          "type": "string"
        },
        "url": {
          "type": "string"
        },
        "weatherInfo": {
          "type": "string"
        },
        "activities": {
          "type": "array",
          "items": {
            "type": "object",
            "properties": {
              "id": {
                "type": "number"
              },
              "name": {
                "type": "string"
              }
            }
          }
        },
        "addresses": {
          "type": "array",
          "items": {
            "type": "object",
            "properties": {
              "id": {
                "type": "number"
              },
              "postalCode": {
                "type": "string"
              },
              "city": {
                "type": "string"
              },
              "stateCode": {
                "type": "string"
              },
              "line1": {
                "type": "string"
              },
              "type": {
                "type": "string",
                "enum": [
                  "physical",
                  "mailing"
                ]
              },
              "line3": {
                "type": "string"
              },
              "line2": {
                "type": "string"
              }
            }
          }
        },
        "contacts": {
          "type": "array",
          "items": {
            "type": "object",
            "properties": {
              "id": {
                "type": "number"
              },
              "phoneNumbers": {
                "type": "array",
                "items": {
                  "type": "object",
                  "properties": {
                    "id": {
                      "type": "number"
                    },
                    "phoneNumber": {
                      "type": "string"
                    },
                    "description": {
                      "type": "string"
                    },
                    "extension": {
                      "type": "string"
                    },
                    "type": {
                      "type": "string",
                      "enum": [
                        "voice",
                        "fax",
                        "tty"
                      ]
                    }
                  }
                }
              },
              "emailAddresses": {
                "type": "object",
                "properties": {
                  "id": {
                    "type": "number"
                  },
                  "description": {
                    "type": "string"
                  },
                  "emailAddress": {
                    "type": "string"
                  }
                }
              }
            }
          }
        },
        "entranceFees": {
          "type": "array",
          "items": {
            "type": "object",
            "properties": {
              "id": {
                "type": "number"
              },
              "cost": {
                "type": "string"
              },
              "description": {
                "type": "string"
              },
              "title": {
                "type": "string"
              }
            }
          }
        },
        "entrancePasses": {
          "type": "array",
          "items": {
            "type": "object",
            "properties": {
              "id": {
                "type": "number"
              },
              "cost": {
                "type": "number"
              },
              "description": {
                "type": "string"
              },
              "title": {
                "type": "string"
              }
            }
          }
        },
        "operatingHours": {
          "type": "array",
          "items": {
            "type": "object",
            "properties": {
              "id": {
                "type": "number"
              },
              "name": {
                "type": "string"
              },
              "description": {
                "type": "string"
              },
              "standardHours": {
                "type": "object",
                "properties": {
                  "id": {
                    "type": "number"
                  },
                  "sunday": {
                    "type": "string"
                  },
                  "monday": {
                    "type": "string"
                  },
                  "tuesday": {
                    "type": "string"
                  },
                  "wednesday": {
                    "type": "string"
                  },
                  "thursday": {
                    "type": "string"
                  },
                  "friday": {
                    "type": "string"
                  },
                  "saturday": {
                    "type": "string"
                  }
                }
              },
              "exceptions": {
                "type": "array",
                "items": {
                  "type": "object",
                  "properties": {
                    "id": {
                      "type": "number"
                    },
                    "name": {
                      "type": "string"
                    },
                    "startDate": {
                      "type": "string"
                    },
                    "endDate": {
                      "type": "string"
                    },
                    "exceptionHours": {
                      "type": "object",
                      "properties": {
                        "id": {
                          "type": "number"
                        },
                        "sunday": {
                          "type": "string"
                        },
                        "monday": {
                          "type": "string"
                        },
                        "tuesday": {
                          "type": "string"
                        },
                        "wednesday": {
                          "type": "string"
                        },
                        "thursday": {
                          "type": "string"
                        },
                        "friday": {
                          "type": "string"
                        },
                        "saturday": {
                          "type": "string"
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        },
        "images": {
          "type": "array",
          "items": {
            "type": "object",
            "properties": {
              "id": {
                "type": "number"
              },
              "credit": {
                "type": "string"
              },
              "altText": {
                "type": "string"
              },
              "title": {
                "type": "string"
              },
              "caption": {
                "type": "string"
              },
              "url": {
                "type": "string"
              }
            }
          }
        },
        "topics": {
          "type": "array",
          "items": {
            "type": "object",
            "properties": {
              "id": {
                "type": "number"
              },
              "name": {
                "type": "string"
              }
            }
          }
        }
      }
    },
    "Activity": {
      "properties": {
        "id": {
          "type": "number"
        },
        "name": {
          "type": "string"
        }
      }
    },
    "Address": {
      "properties": {
        "id": {
          "type": "number"
        },
        "postalCode": {
          "type": "string"
        },
        "city": {
          "type": "string"
        },
        "stateCode": {
          "type": "string"
        },
        "line1": {
          "type": "string"
        },
        "type": {
          "type": "string",
          "enum": [
            "physical",
            "mailing"
          ]
        },
        "line3": {
          "type": "string"
        },
        "line2": {
          "type": "string"
        }
      }
    },
    "Contact": {
      "properties": {
        "id": {
          "type": "number"
        },
        "phoneNumbers": {
          "type": "array",
          "items": {
            "type": "object",
            "properties": {
              "id": {
                "type": "number"
              },
              "phoneNumber": {
                "type": "string"
              },
              "description": {
                "type": "string"
              },
              "extension": {
                "type": "string"
              },
              "type": {
                "type": "string",
                "enum": [
                  "voice",
                  "fax",
                  "tty"
                ]
              }
            }
          }
        }
      }
    },
    "EmailAddress": {
      "properties": {
        "id": {
          "type": "number"
        },
        "description": {
          "type": "string"
        },
        "emailAddress": {
          "type": "string"
        }
      }
    },
    "EntranceFee": {
      "properties": {
        "id": {
          "type": "number"
        },
        "cost": {
          "type": "string"
        },
        "description": {
          "type": "string"
        },
        "title": {
          "type": "string"
        }
      }
    },
    "EntrancePasses": {
      "properties": {
        "id": {
          "type": "number"
        },
        "cost": {
          "type": "number"
        },
        "description": {
          "type": "string"
        },
        "title": {
          "type": "string"
        }
      }
    },
    "Exception": {
      "properties": {
        "id": {
          "type": "number"
        },
        "name": {
          "type": "string"
        },
        "startDate": {
          "type": "string"
        },
        "endDate": {
          "type": "string"
        },
        "exceptionHours": {
          "type": "object",
          "properties": {
            "id": {
              "type": "number"
            },
            "sunday": {
              "type": "string"
            },
            "monday": {
              "type": "string"
            },
            "tuesday": {
              "type": "string"
            },
            "wednesday": {
              "type": "string"
            },
            "thursday": {
              "type": "string"
            },
            "friday": {
              "type": "string"
            },
            "saturday": {
              "type": "string"
            }
          }
        }
      }
    },
    "ExceptionHour": {
      "properties": {
        "id": {
          "type": "number"
        },
        "sunday": {
          "type": "string"
        },
        "monday": {
          "type": "string"
        },
        "tuesday": {
          "type": "string"
        },
        "wednesday": {
          "type": "string"
        },
        "thursday": {
          "type": "string"
        },
        "friday": {
          "type": "string"
        },
        "saturday": {
          "type": "string"
        }
      }
    },
    "Image": {
      "properties": {
        "id": {
          "type": "number"
        },
        "credit": {
          "type": "string"
        },
        "altText": {
          "type": "string"
        },
        "title": {
          "type": "string"
        },
        "caption": {
          "type": "string"
        },
        "url": {
          "type": "string"
        }
      }
    },
    "OperatingHour": {
      "properties": {
        "id": {
          "type": "number"
        },
        "name": {
          "type": "string"
        },
        "description": {
          "type": "string"
        },
        "standardHours": {
          "type": "object",
          "properties": {
            "id": {
              "type": "number"
            },
            "sunday": {
              "type": "string"
            },
            "monday": {
              "type": "string"
            },
            "tuesday": {
              "type": "string"
            },
            "wednesday": {
              "type": "string"
            },
            "thursday": {
              "type": "string"
            },
            "friday": {
              "type": "string"
            },
            "saturday": {
              "type": "string"
            }
          }
        },
        "exceptions": {
          "type": "array",
          "items": {
            "type": "object",
            "properties": {
              "id": {
                "type": "number"
              },
              "name": {
                "type": "string"
              },
              "startDate": {
                "type": "string"
              },
              "endDate": {
                "type": "string"
              },
              "exceptionHours": {
                "type": "object",
                "properties": {
                  "id": {
                    "type": "number"
                  },
                  "sunday": {
                    "type": "string"
                  },
                  "monday": {
                    "type": "string"
                  },
                  "tuesday": {
                    "type": "string"
                  },
                  "wednesday": {
                    "type": "string"
                  },
                  "thursday": {
                    "type": "string"
                  },
                  "friday": {
                    "type": "string"
                  },
                  "saturday": {
                    "type": "string"
                  }
                }
              }
            }
          }
        }
      }
    },
    "PhoneNumber": {
      "properties": {
        "id": {
          "type": "number"
        },
        "phoneNumber": {
          "type": "string"
        },
        "description": {
          "type": "string"
        },
        "extension": {
          "type": "string"
        },
        "type": {
          "type": "string",
          "enum": [
            "voice",
            "fax",
            "tty"
          ]
        }
      }
    },
    "StandardHour": {
      "properties": {
        "id": {
          "type": "number"
        },
        "sunday": {
          "type": "string"
        },
        "monday": {
          "type": "string"
        },
        "tuesday": {
          "type": "string"
        },
        "wednesday": {
          "type": "string"
        },
        "thursday": {
          "type": "string"
        },
        "friday": {
          "type": "string"
        },
        "saturday": {
          "type": "string"
        }
      }
    },
    "Topic": {
      "properties": {
        "id": {
          "type": "number"
        },
        "name": {
          "type": "string"
        }
      }
    }
  }
}
```

package com.harystolho.wetter.core.exception

/**
 * Generic exception used to indicate something unexpected happened while reading/loading a resource
 * in a repository
 */
class ResourceReadException : RuntimeException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}
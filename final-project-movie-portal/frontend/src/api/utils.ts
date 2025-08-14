export function getAPIErrorStatus(error: Error): number | undefined {
  if (error instanceof Error) {
    // @ts-expect-error err WILL have a status property at runtime
    const status = error.status;
    if (status) {
      return status;
    }
  }
}

interface ErrorResponse {
  unauthorized?: string;
  conflict?: string;
}

const defaultErrorResponse: ErrorResponse = {
  unauthorized: 'Unauthorized',
  conflict: 'Conflict',
};

export function getAPIError(
  error: Error | unknown,
  errorResponse: ErrorResponse = defaultErrorResponse
): Error {
  if (!(error instanceof Error)) {
    return new Error(String(error));
  }
  const status = getAPIErrorStatus(error);
  errorResponse = { ...defaultErrorResponse, ...errorResponse };
  if (status === 400) {
    // @ts-expect-error err WILL have a response property at runtime
    const errorData = error.data as Record<string, string>;
    return new Error(Object.values(errorData).join(', '));
  } else if (status === 401) {
    return new Error(errorResponse.unauthorized);
  } else if (status === 409) {
    return new Error(errorResponse.conflict);
  }
  return error;
}

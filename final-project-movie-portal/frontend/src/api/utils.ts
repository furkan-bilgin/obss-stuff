export function getAPIErrorStatus(error: Error): number | undefined {
  if (error instanceof Error) {
    // @ts-expect-error err WILL have a status property at runtime
    const status = error.status;
    if (status) {
      return status;
    }
  }
}

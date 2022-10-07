import { InputMaybe, PaginationInput, SortInput, SortOrder } from '../generatedTypes'

const resolve = (path: string, obj: Record<string, any>) => {
  return path.split('.').reduce(function (prev, curr) {
    return prev ? prev[curr] : null
  }, obj || self)
}

export const getPaginatedResult = <T extends Record<string, any>>(variables: { pagination: InputMaybe<PaginationInput>, sorting: InputMaybe<SortInput> }, rows: T[]) => {
  let results = rows.slice()
  if (variables.sorting) {
    results = getSortedResult(variables.sorting, results)
  }

  const limit = variables.pagination?.first ?? variables.pagination?.last ?? 5
  const after = (variables.pagination?.after && results.findIndex(row => row._id === variables.pagination?.after) + 1) || 0
  const before = (variables.pagination?.before && results.findIndex(row => row._id === variables.pagination?.before) - 1) || 0

  let edges
  if (after) {
    edges = results.slice(after, after + limit)
  } else if (before) {
    edges = results.slice(before + 1 - limit, before + 1)
  } else {
    edges = results.slice(0, limit)
  }

  return {
    pageInfo: {
      __typename: 'PageInfo',
      total: results.length,
      hasNextPage: true,
      hasPreviousPage: false,
      startCursor: edges[0]._id,
      endCursor: edges[edges.length - 1]?._id,
    },
    nodes: edges
  }
}

export const getSortedResult = <T extends Record<string, any>>(sort: InputMaybe<SortInput>, rows: T[]) => {
  const rowsCopy = rows.slice()

  if (sort?.sortBy) {
    rowsCopy.sort((a, b) => {
      const valueA = resolve(sort?.sortBy, a)
      const valueB = resolve(sort?.sortBy, b)

      if (sort.order === SortOrder.Asc) {
        return valueA < valueB ? -1: 1
      } else {
        return valueA > valueB ? -1: 1
      }
    })
  }

  return rowsCopy
}
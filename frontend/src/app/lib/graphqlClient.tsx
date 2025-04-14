// lib/graphqlClient.ts
import { GraphQLClient, gql } from 'graphql-request';

const API_ENDPOINT = process.env.NEXT_PUBLIC_GRAPHQL_ENDPOINT; // Set this in your .env.local file

if (!API_ENDPOINT) {
    throw new Error('NEXT_PUBLIC_GRAPHQL_ENDPOINT is not defined in your environment variables.');
}

const graphQLClient = new GraphQLClient(API_ENDPOINT);

export async function request<T>(query: string, variables?: Record<string, unknown>): Promise<T> {
    try {
        return await graphQLClient.request<T>(gql`${query}`, variables);
    } catch (error) {
    console.error('GraphQL request failed:', error);
        throw error;
    }
}